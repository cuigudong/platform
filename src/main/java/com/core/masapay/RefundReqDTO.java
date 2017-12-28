package com.core.masapay;

public class RefundReqDTO {
	// 基本参数
	private String version = "2.0";
	private String merchantId = "801128553112016";
	private String charset = "utf-8";
	private String language = "cn";
	private String signType = "SHA256";

	// 业务参数
	private String refundOrderNo = "201710250000001";
	private String merchantOrderNo = "2017101804399670";
	private String masapayOrderNo = "OG102017102300117950";
	private String refundAmount = "100";
	private String currencyCode = "CNY";
	private String refundSubmitTime = "20171025111110";
	private String callbackUrl = "http://goapy.com.css.sh";
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

	public String getRefundOrderNo() {
		return refundOrderNo;
	}

	public void setRefundOrderNo(String refundOrderNo) {
		this.refundOrderNo = refundOrderNo;
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

	public String getMasapayOrderNo() {
		return masapayOrderNo;
	}

	public void setMasapayOrderNo(String masapayOrderNo) {
		this.masapayOrderNo = masapayOrderNo;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getRefundSubmitTime() {
		return refundSubmitTime;
	}

	public void setRefundSubmitTime(String refundSubmitTime) {
		this.refundSubmitTime = refundSubmitTime;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	@Override
	public String toString() {
		return "MasaPayRefundOrder [version=" + version + ", merchantId=" + merchantId + ", charset=" + charset
				+ ", language=" + language + ", signType=" + signType + ", refundOrderNo=" + refundOrderNo
				+ ", merchantOrderNo=" + merchantOrderNo + ", masapayOrderNo=" + masapayOrderNo + ", refundAmount="
				+ refundAmount + ", currencyCode=" + currencyCode + ", refundSubmitTime=" + refundSubmitTime
				+ ", callbackUrl=" + callbackUrl + ", signMsg=" + signMsg + "]";
	}

	public String signatureData(String key) {
		return "version=" + version + "&merchantId=" + merchantId + "&charset=" + charset + "&language=" + language
				+ "&signType=" + signType + "&refundOrderNo=" + refundOrderNo + "&merchantOrderNo=" + merchantOrderNo
				+ "&masapayOrderNo=" + masapayOrderNo + "&refundAmount=" + refundAmount + "&currencyCode="
				+ currencyCode + "&refundSubmitTime=" + refundSubmitTime + "&callbackUrl=" + callbackUrl + "&key="
				+ key;
	}
}
