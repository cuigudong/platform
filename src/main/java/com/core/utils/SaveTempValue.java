package com.core.utils;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SaveTempValue {

	private SaveTempValue() {
	}

	private static SaveTempValue instance = null;

	public static SaveTempValue getInstance() {
		if (instance == null) {
			instance = new SaveTempValue();
		}
		return instance;
	}

	private static final Logger logger = LoggerFactory.getLogger(SaveTempValue.class);

	private static final String tokenUrl = "/api/v1/authentication/login";
	private static final String quoteUrl = "/api/v1/rates/quote";
	private static final String invalidConversionDatesUrl = "/api/v1/reference/invalid_conversion_dates";

	private String TOKEN = "token";

	private String url = "https://api-demo.airwallex.com";

	private String clientId = "yQIBd3ETS6er_Flwpx5Yqw";

	private String apiKey = "af48ad450818e7eb49bec55210747fc1cd7e0fc66ba4bd20fd187c1189bf51789eed9dd5ebb64e5b8ec9d336a6080e26";

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
			return "��������쳣";
		}
	}

	public void comOrders() {
		Map<String, BigDecimal> orders = getMap();
		Iterator it = orders.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry ent = (Map.Entry) it.next();
			String keyt = ent.getKey().toString();
			String valuet = ent.getValue().toString();
			System.out.println(keyt + "*" + valuet);
		}

	}

	public static void main(String args[]) throws InterruptedException {
		SaveTempValue.getInstance().comOrders();
		/*for (int i = 0; i < 1000; i++) {
			System.out.println(SaveTempValue.getInstance().getAccessToken());
			Thread.sleep(30 * 1000);
		}*/

	}

	private Map<String, BigDecimal> getMap() {
		Map<String, BigDecimal> orders = new TreeMap<String, BigDecimal>();
		orders.put("1", new BigDecimal(10));
		orders.put("2", new BigDecimal(20));
		orders.put("3", new BigDecimal(30));
		orders.put("4", new BigDecimal(40));
		orders.put("5", new BigDecimal(50));
		orders.put("6", new BigDecimal(60));
		orders.put("7", new BigDecimal(70));
		orders.put("8", new BigDecimal(80));
		orders.put("9", new BigDecimal(90));
		orders.put("10", new BigDecimal(100));
		return orders;
	}

}
