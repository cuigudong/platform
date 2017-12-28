package com.core.airwallex;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.core.model.Address;
import com.core.model.Balances;
import com.core.model.BankDetails;
import com.core.model.Beneficiary;
import com.core.model.Payer;
import com.core.model.Payment;
import com.core.model.PaymentA;
import com.core.utils.BeanMapper;
import com.core.utils.JacksonUtil;
import com.core.utils.SaveTempValue;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class AirwallexReq {
	private static AirwallexReq instance = null;

	public static AirwallexReq getInstance() {
		if (instance == null) {
			instance = new AirwallexReq();
		}
		return instance;
	}

	private static final Logger logger = LoggerFactory.getLogger(SaveTempValue.class);

	private static final String tokenUrl = "/api/v1/authentication/login";
	private static final String quoteUrl = "/api/v1/rates/quote";
	private static final String invalidConversionDatesUrl = "/api/v1/reference/invalid_conversion_dates";
	private static final String balancesUrl = "/api/v1/balances/current";
	private static final String createPaymentUrl = "/api/v1/payments/create";

	private String TOKEN = "token";

	private String url = "https://api-demo.airwallex.com";

	private String clientId = "yQIBd3ETS6er_Flwpx5Yqw";

	private String apiKey = "af48ad450818e7eb49bec55210747fc1cd7e0fc66ba4bd20fd187c1189bf51789eed9dd5ebb64e5b8ec9d336a6080e26";

	public String getCurrentBalances() throws UnirestException {
		String token = getAccessToken();
		StringBuffer urlStr = new StringBuffer(url);
		urlStr.append(balancesUrl);
		HttpResponse<String> response = Unirest.get(urlStr.toString()).header("Content-Type", "application/json")
				.header("Authorization", token).asString();
		System.out.println(response.getBody());
		return response.getBody();
	}

	private Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1)
			.expireAfterWrite(60 * 60, TimeUnit.SECONDS).build();

	public String getAccessToken() {
		String token = cache.getIfPresent(TOKEN);
		if (token == null) {
			token = getToken();
			cache.put(TOKEN, token);
		}
		return token;
	}

	private String getToken() {
		String tokenValue = "";
		try {
			HttpResponse<String> response = Unirest.post(url + tokenUrl).header("Content-Type", "application/json")
					.header("x-client-id", clientId).header("x-api-key", apiKey).asString();
			System.out.println(String.format("token resp : %s", response.getBody()));
			if (response.getStatus() == 201) {
				@SuppressWarnings("unchecked")
				Map<String, String> map = JacksonUtil.readValue(response.getBody(), Map.class);
				tokenValue = "Bearer " + map.get("token");
				return tokenValue;
			} else {
				return response.getStatusText();
			}
		} catch (UnirestException e) {
			logger.error("airwallex token request error:", e);
			return "请求异常";
		}
	}

	public String createPayment(String json) throws UnirestException {
		String token = getAccessToken();
		StringBuffer urlStr = new StringBuffer(url);
		urlStr.append(createPaymentUrl);
		HttpResponse<String> response = Unirest.post(urlStr.toString()).header("Content-Type", "application/json")
				.header("Authorization", token).body(json).asString();
		System.out.println(response.getBody());
		return response.getBody();
	}

	public static String createPayments(String token) throws UnirestException {
		HttpResponse<String> response = Unirest.post("https://api-demo.airwallex.com/api/v1/payments/create")
				.header("Content-Type", "application/json").header("Authorization", token)
				.body("{\"beneficiary\":{\"address\":{\"city\":\"Melbourne\",\"country_code\":\"AU\",\"postcode\":3000,\"state\":\"VIC\",\"street_address\":\"15 Williams Street\"},\"bank_details\":{\"account_currency\":\"AUD\",\"account_name\":\"Lee Da Ming\",\"account_number\":12750852,\"account_routing_type1\":\"bsb\",\"account_routing_type2\":\"sort_code\",\"account_routing_value1\":\"083064\",\"account_routing_value2\":123456,\"bank_country_code\":\"AU\",\"bank_name\":\"National Australia Bank\",\"bank_street_address\":\"500 Bourke Street, Melbourne 3000, Australia\",\"iban\":\"ES8023100001180000012345\",\"swift_code\":\"CTBAAU2S\"},\"company_name\":\"Complete Concrete Pty Ltd\",\"email\":\"john.walker@completeconcrete.com.au\",\"entity_type\":\"COMPANY\",\"first_name\":\"John\",\"last_name\":\"Walker\"},\"fee_paid_by\":\"PAYER\",\"payer\":{\"address\":{\"city\":\"Melbourne\",\"country_code\":\"AU\",\"postcode\":3000,\"state\":\"VIC\",\"street_address\":\"15 Williams Street\"},\"company_name\":\"Complete Concrete Pty Ltd\",\"date_of_birth\":\"1976-08-26\",\"entity_type\":\"COMPANY\",\"first_name\":\"James\",\"last_name\":\"Smith\"},\"payment_amount\":10000,\"payment_currency\":\"AUD\",\"payment_method\":\"DEFAULT\",\"reason\":\"Settling concrete purchase\",\"reference\":\"PMT1936398\",\"request_id\":\"7f687fe6-dcf4-4462-92fa-80335301d9d2\",\"source_currency\":\"CNY\"}")
				.asString();
		return response.getBody();
	}

	public static void main1(String args[]) throws UnirestException {
		// String jsons = JacksonUtil.writeValueAsString(payment());
		// System.out.println(jsons);
		// Payment payment = JacksonUtil.readValue(jsons, Payment.class);
		// System.out.println(payment.toString());
		// System.out.println(payment.getBeneficiary().toString());
		// System.out.println(payment.getPayer().toString());
		PaymentA paymentA = getPaymentA();
		Payment pay = BeanMapper.map(paymentA, Payment.class);
		Beneficiary benficiary = BeanMapper.map(paymentA, Beneficiary.class);
		Address address = BeanMapper.map(paymentA, Address.class);
		BankDetails bankDetails = BeanMapper.map(paymentA, BankDetails.class);
		benficiary.setAddress(address);
		benficiary.setBank_details(bankDetails);
		pay.setBeneficiary(benficiary);
		Payer payer = new Payer();
		payer.setEntity_type("COMPANY");
		payer.setCompany_name("guofubaoxinxikejiyouxiangongsi");
		Address addr = new Address();
		addr.setCountry_code("CN");
		addr.setFull_address("this is a");
		payer.setAddress(addr);
		pay.setPayer(payer);
		
		 String json = JacksonUtil.writeValueAsString(pay);

		//String json = "{\"beneficiary\":{\"address\":{\"city\":\"Melbourne\",\"country_code\":\"AU\",\"postcode\":3000,\"state\":\"VIC\",\"street_address\":\"15 Williams Street\"},\"bank_details\":{\"account_currency\":\"AUD\",\"account_name\":\"Lee Da Ming\",\"account_number\":12750852,\"account_routing_type1\":\"bsb\",\"account_routing_type2\":\"sort_code\",\"account_routing_value1\":\"083064\",\"account_routing_value2\":123456,\"bank_country_code\":\"AU\",\"bank_name\":\"National Australia Bank\",\"bank_street_address\":\"500 Bourke Street, Melbourne 3000, Australia\",\"iban\":\"ES8023100001180000012345\",\"swift_code\":\"CTBAAU2S\"},\"company_name\":\"Complete Concrete Pty Ltd\",\"email\":\"john.walker@completeconcrete.com.au\",\"entity_type\":\"COMPANY\",\"first_name\":\"John\",\"last_name\":\"Walker\"},\"fee_paid_by\":\"PAYER\",\"payer\":{\"address\":{\"city\":\"Melbourne\",\"country_code\":\"AU\",\"postcode\":3000,\"state\":\"VIC\",\"street_address\":\"15 Williams Street\"},\"company_name\":\"Complete Concrete Pty Ltd\",\"date_of_birth\":\"1976-08-26\",\"entity_type\":\"COMPANY\",\"first_name\":\"James\",\"last_name\":\"Smith\"},\"payment_amount\":10000,\"payment_currency\":\"AUD\",\"payment_method\":\"DEFAULT\",\"reason\":\"Settling concrete purchase\",\"reference\":\"PMT1936398\",\"request_id\":\"7f687fe6-dcf4-4462-92fa-80335301d9d2\",\"source_currency\":\"CNY\"}";
		
		 
		 System.out.println(json);
		String response = AirwallexReq.getInstance().createPayment(json);
		System.out.println(response);
//		String token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxZDI3OGMxZC03MGI3LTRkMGItYjJmYS1kYmZkZjMzNmZiOTQiLCJzdWIiOiJjOTAyMDE3Ny03MTEzLTRiYTctYWJmYy01OTcwYTcxZTU4YWIiLCJpYXQiOjE1MDU4MjEzMDQsImV4cCI6MTUwNTgzMzMwNH0.Xd_odZJPvqicd6bwDmGtrvzkcVQ3kEHlNxR2GIn-ljk";
//		System.out.println(createPayments(token));

	}

	public static PaymentA getPaymentA() {
		PaymentA paymentA = new PaymentA();
		paymentA.setPayment_amount("1000");
		paymentA.setPayment_currency("AUD");
		paymentA.setReason("goods_trade_offline");
		paymentA.setReference("test");
		paymentA.setRequest_id(UUID.randomUUID().toString());
		paymentA.setSource_currency("USD");
		paymentA.setFee_paid_by("BENEFICIARY");

		paymentA.setCountry_code("US");
		paymentA.setCity("HuaShengDun");
		paymentA.setFull_address("Huanghouqu");
		paymentA.setPostcode("10086");
		paymentA.setState("zhouzhouzhou");

		paymentA.setAccount_name("huaqi");
		paymentA.setAccount_number("1001010010");
		paymentA.setBank_country_code("AU");
		paymentA.setBank_name("huaqiqi");
		paymentA.setAccount_routing_type1("bsb");
		paymentA.setAccount_routing_value1("083064");
		paymentA.setSwift_code("12213123211");
		paymentA.setAccount_currency("AUD");

		paymentA.setEntity_type("COMPANY");
		paymentA.setCompany_name("guofubaoxinxikejiyouxiangongsi");
		paymentA.setPayment_method("DEFAULT");
		return paymentA;
	}

	public static Payment payment() {
		Payment payment = new Payment();
		payment.setPayment_amount("1000");
		payment.setPayment_currency("CNY");
		payment.setReason("goods_trade_offline");
		payment.setReference("test");
		payment.setRequest_id(UUID.randomUUID().toString());
		payment.setSource_currency("HNK");
		Beneficiary beneficiary = new Beneficiary();
		Address address = new Address();
		address.setCountry_code("US");
		address.setCity("HuaShengDun");
		address.setFull_address("Huanghouqu");
		address.setPostcode("10086");
		address.setState("zhouzhouzhou");
		beneficiary.setAddress(address);
		BankDetails bank_detail = new BankDetails();
		bank_detail.setAccount_name("huaqi");
		bank_detail.setAccount_number("1001010010");
		bank_detail.setBank_country_code("USA");
		bank_detail.setBank_name("huaqiqi");
		bank_detail.setAccount_routing_type1("SWF");
		bank_detail.setAccount_routing_value1("22323");
		bank_detail.setSwift_code("12213123211");
		beneficiary.setBank_details(bank_detail);
		beneficiary.setEntity_type("COMPANY");
		beneficiary.setCompany_name("apple tld. etc");
		payment.setBeneficiary(beneficiary);
		Payer payer = new Payer();
		payer.setEntity_type("COMPANY");
		payer.setCompany_name("guofubaoxinxikejiyouxiangongsi");
		Address addressPayer = new Address();
		addressPayer.setCountry_code("CN");
		payer.setAddress(addressPayer);
		payment.setPayer(payer);
		return payment;
	}

	public static void main(String args[]) throws UnirestException {
		String json=AirwallexReq.getInstance().getCurrentBalances();
		String str = "[{\"available_amount\":992146,\"currency\":\"HKD\",\"pending_amount\":0,\"total_amount\":992146},{\"available_amount\":0,\"currency\":\"AED\",\"pending_amount\":0,\"total_amount\":0},{\"available_amount\":0,\"currency\":\"EUR\",\"pending_amount\":0,\"total_amount\":0},{\"available_amount\":1000.0,\"currency\":\"USD\",\"pending_amount\":0,\"total_amount\":1000.0},{\"available_amount\":992146,\"currency\":\"HKD\",\"pending_amount\":0,\"total_amount\":992146},{\"available_amount\":0,\"currency\":\"AED\",\"pending_amount\":0,\"total_amount\":0},{\"available_amount\":0,\"currency\":\"EUR\",\"pending_amount\":0,\"total_amount\":0},{\"available_amount\":1000.0,\"currency\":\"USD\",\"pending_amount\":0,\"total_amount\":1000.0},{\"available_amount\":992146,\"currency\":\"HKD\",\"pending_amount\":0,\"total_amount\":992146},{\"available_amount\":0,\"currency\":\"AED\",\"pending_amount\":0,\"total_amount\":0},{\"available_amount\":0,\"currency\":\"EUR\",\"pending_amount\":0,\"total_amount\":0},{\"available_amount\":1000.0,\"currency\":\"USD\",\"pending_amount\":0,\"total_amount\":1000.0}]";
		List<Balances> balances = JacksonUtil.jsonToListBean(json, Balances.class);
		for (Balances balance : balances) {
			System.out.println(balance.toString());
		}
		Balances balance = new Balances();
		balance.setCurrency("RMB");
		balance.setAvailable_amount(new BigDecimal(100));
		System.out.println(JacksonUtil.writeValueAsString(balance));

	}

}
