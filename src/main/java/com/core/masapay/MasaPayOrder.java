package com.core.masapay;

public class MasaPayOrder {
	// 基本参数
	private String version = "1.7";
	private String merchantId = "801128553112016";
	private String charset = "utf-8";
	private String language = "cn";
	private String signType = "SHA256";

	// 业务参数
	private String merchantOrderNo = "2017111804399670";
	private String goodsName = "波音747";
	private String goodsDesc = "会飞的大灰机";
	private String orderExchange = "2";
	private String currencyCode = "USD";
	private String orderAmount = "1000";
	private String flag3D = "N";
	private String submitTime = "20171023020101";
	private String expiryTime = "20171023102121";
	private String bgUrl = "http://biscbrmb248.gopay.com.cn/cbrmb/notify/orderNoitfy";
	private String transType = "EDC";
	private String dccRateOrderId;
	private String ext1;
	private String ext2;
	private String remark;
	// 支付参数
	private String payMode = "10";
	private String orgCode = "VISA";
	private String cardNumber = "4062540301082212";
	private String cardHolderFirstName="rich";
	private String cardHolderLastName="thief";
	private String cardExpirationMonth = "12";
	private String cardExpirationYear = "20";
	private String securityCode = "123";
	private String cardHolderEmail = "136253588@qq.com";
	private String cardHolderPhoneNumber = "13681144302";
	private String payExt1;
	private String payExt2;
	// 账单信息
	private String billName = "rich thief ";
	private String billAddress = "2301 Kalakaua Ave,Honolulu,HI";
	private String billPostalCode = "96898111";
	private String billCompany="the cheesecake factory";
	private String billCountry = "USA";
	private String billState = "HI";
	private String billCity = "Hawaii";
	private String billEmail = "136253588@qq.com";
	private String billPhoneNumber = "13681144302";
	// 收货信息
	private String shippingName = "rich thief";
	private String shippingAddress = "2301 Kalakaua Ave,Honolulu,HI";
	private String shippingPostalCode = "96898";
	private String shippingCompany = "the cheesecake factory";
	private String shippingCountry = "USA";
	private String shippingState = "HI";
	private String shippingCity = "Hawaii";
	private String shippingEmail = "136253588@qq.com";
	private String shippingPhoneNumber = "13681144302";
	// 风控信息
	private String deviceFingerprintID = "m8526m801134112311001_1F0D5400-856B-4C7C-5C54-27D9385C4D95";
	private String payerName;
	private String payerMobile;
	private String payerEmail;
	private String registerUserId;
	private String registerUserEmail = "136253588@qq.com";
	private String registerTime = "20171023020101";
	private String registerIp = "202.96.209.16";
	private String registerTerminal = "00";
	private String orderIp = "202.96.209.16";
	private String orderTerminal = "00";
	private String referer;
	private String ext3;
	private String ext4;
	private String signMsg;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getOrderExchange() {
		return orderExchange;
	}

	public void setOrderExchange(String orderExchange) {
		this.orderExchange = orderExchange;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getFlag3D() {
		return flag3D;
	}

	public void setFlag3D(String flag3d) {
		flag3D = flag3d;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(String expiryTime) {
		this.expiryTime = expiryTime;
	}

	public String getBgUrl() {
		return bgUrl;
	}

	public void setBgUrl(String bgUrl) {
		this.bgUrl = bgUrl;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getDccRateOrderId() {
		return dccRateOrderId;
	}

	public void setDccRateOrderId(String dccRateOrderId) {
		this.dccRateOrderId = dccRateOrderId;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardHolderFirstName() {
		return cardHolderFirstName;
	}

	public void setCardHolderFirstName(String cardHolderFirstName) {
		this.cardHolderFirstName = cardHolderFirstName;
	}

	public String getCardHolderLastName() {
		return cardHolderLastName;
	}

	public void setCardHolderLastName(String cardHolderLastName) {
		this.cardHolderLastName = cardHolderLastName;
	}

	public String getCardExpirationMonth() {
		return cardExpirationMonth;
	}

	public void setCardExpirationMonth(String cardExpirationMonth) {
		this.cardExpirationMonth = cardExpirationMonth;
	}

	public String getCardExpirationYear() {
		return cardExpirationYear;
	}

	public void setCardExpirationYear(String cardExpirationYear) {
		this.cardExpirationYear = cardExpirationYear;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getCardHolderEmail() {
		return cardHolderEmail;
	}

	public void setCardHolderEmail(String cardHolderEmail) {
		this.cardHolderEmail = cardHolderEmail;
	}

	public String getCardHolderPhoneNumber() {
		return cardHolderPhoneNumber;
	}

	public void setCardHolderPhoneNumber(String cardHolderPhoneNumber) {
		this.cardHolderPhoneNumber = cardHolderPhoneNumber;
	}

	public String getPayExt1() {
		return payExt1;
	}

	public void setPayExt1(String payExt1) {
		this.payExt1 = payExt1;
	}

	public String getPayExt2() {
		return payExt2;
	}

	public void setPayExt2(String payExt2) {
		this.payExt2 = payExt2;
	}

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public String getBillAddress() {
		return billAddress;
	}

	public void setBillAddress(String billAddress) {
		this.billAddress = billAddress;
	}

	public String getBillPostalCode() {
		return billPostalCode;
	}

	public void setBillPostalCode(String billPostalCode) {
		this.billPostalCode = billPostalCode;
	}

	public String getBillCompany() {
		return billCompany;
	}

	public void setBillCompany(String billCompany) {
		this.billCompany = billCompany;
	}

	public String getBillCountry() {
		return billCountry;
	}

	public void setBillCountry(String billCountry) {
		this.billCountry = billCountry;
	}

	public String getBillState() {
		return billState;
	}

	public void setBillState(String billState) {
		this.billState = billState;
	}

	public String getBillCity() {
		return billCity;
	}

	public void setBillCity(String billCity) {
		this.billCity = billCity;
	}

	public String getBillEmail() {
		return billEmail;
	}

	public void setBillEmail(String billEmail) {
		this.billEmail = billEmail;
	}

	public String getBillPhoneNumber() {
		return billPhoneNumber;
	}

	public void setBillPhoneNumber(String billPhoneNumber) {
		this.billPhoneNumber = billPhoneNumber;
	}

	public String getShippingName() {
		return shippingName;
	}

	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingPostalCode() {
		return shippingPostalCode;
	}

	public void setShippingPostalCode(String shippingPostalCode) {
		this.shippingPostalCode = shippingPostalCode;
	}

	public String getShippingCompany() {
		return shippingCompany;
	}

	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}

	public String getShippingCountry() {
		return shippingCountry;
	}

	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}

	public String getShippingState() {
		return shippingState;
	}

	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}

	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public String getShippingEmail() {
		return shippingEmail;
	}

	public void setShippingEmail(String shippingEmail) {
		this.shippingEmail = shippingEmail;
	}

	public String getShippingPhoneNumber() {
		return shippingPhoneNumber;
	}

	public void setShippingPhoneNumber(String shippingPhoneNumber) {
		this.shippingPhoneNumber = shippingPhoneNumber;
	}

	public String getDeviceFingerprintID() {
		return deviceFingerprintID;
	}

	public void setDeviceFingerprintID(String deviceFingerprintID) {
		this.deviceFingerprintID = deviceFingerprintID;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getPayerMobile() {
		return payerMobile;
	}

	public void setPayerMobile(String payerMobile) {
		this.payerMobile = payerMobile;
	}

	public String getPayerEmail() {
		return payerEmail;
	}

	public void setPayerEmail(String payerEmail) {
		this.payerEmail = payerEmail;
	}

	public String getRegisterUserId() {
		return registerUserId;
	}

	public void setRegisterUserId(String registerUserId) {
		this.registerUserId = registerUserId;
	}

	public String getRegisterUserEmail() {
		return registerUserEmail;
	}

	public void setRegisterUserEmail(String registerUserEmail) {
		this.registerUserEmail = registerUserEmail;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public String getRegisterTerminal() {
		return registerTerminal;
	}

	public void setRegisterTerminal(String registerTerminal) {
		this.registerTerminal = registerTerminal;
	}

	public String getOrderIp() {
		return orderIp;
	}

	public void setOrderIp(String orderIp) {
		this.orderIp = orderIp;
	}

	public String getOrderTerminal() {
		return orderTerminal;
	}

	public void setOrderTerminal(String orderTerminal) {
		this.orderTerminal = orderTerminal;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	public String getExt4() {
		return ext4;
	}

	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	@Override
	public String toString() {
		return "version=" + version + "&merchantId=" + merchantId + "&charset=" + charset + "&language=" + language
				+ "&signType=" + signType + "&merchantOrderNo=" + merchantOrderNo + "&goodsName=" + goodsName
				+ "&goodsDesc=" + goodsDesc + "&orderExchange=" + orderExchange + "&currencyCode=" + currencyCode
				+ "&orderAmount=" + orderAmount + "&flag3D=" + flag3D + "&submitTime=" + submitTime + "&expiryTime="
				+ expiryTime + "&bgUrl=" + bgUrl + "&transType=" + transType + "&dccRateOrderId=" + dccRateOrderId
				+ "&ext1=" + ext1 + "&ext2=" + ext2 + "&remark=" + remark + "&payMode=" + payMode + "&orgCode="
				+ orgCode + "&cardNumber=" + cardNumber + "&cardHolderFirstName=" + cardHolderFirstName
				+ "&cardHolderLastName=" + cardHolderLastName + "&cardExpirationMonth=" + cardExpirationMonth
				+ "&cardExpirationYear=" + cardExpirationYear + "&securityCode=" + securityCode + "&cardHolderEmail="
				+ cardHolderEmail + "&cardHolderPhoneNumber=" + cardHolderPhoneNumber + "&payExt1=" + payExt1
				+ "&payExt2=" + payExt2 + "&billName=" + billName + "&billAddress=" + billAddress + "&billPostalCode="
				+ billPostalCode + "&billCompany=" + billCompany + "&billCountry=" + billCountry + "&billState="
				+ billState + "&billCity=" + billCity + "&billEmail=" + billEmail + "&billPhoneNumber="
				+ billPhoneNumber + "&shippingName=" + shippingName + "&shippingAddress=" + shippingAddress
				+ "&shippingPostalCode=" + shippingPostalCode + "&shippingCompany=" + shippingCompany
				+ "&shippingCountry=" + shippingCountry + "&shippingState=" + shippingState + "&shippingCity="
				+ shippingCity + "&shippingEmail=" + shippingEmail + "&shippingPhoneNumber=" + shippingPhoneNumber
				+ "&deviceFingerprintID=" + deviceFingerprintID + "&payerName=" + payerName + "&payerMobile="
				+ payerMobile + "&payerEmail=" + payerEmail + "&registerUserId=" + registerUserId
				+ "&registerUserEmail=" + registerUserEmail + "&registerTime=" + registerTime + "&registerIp="
				+ registerIp + "&registerTerminal=" + registerTerminal + "&orderIp=" + orderIp + "&orderTerminal="
				+ orderTerminal + "&referer=" + referer + "&ext3=" + ext3 + "&ext4=" + ext4 + "&signMsg=" + signMsg
				+ "";
	}

}
