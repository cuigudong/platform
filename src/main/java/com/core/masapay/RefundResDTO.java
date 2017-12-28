package com.core.masapay;

public class RefundResDTO {
	// 基本参数
	private String version;
	private String merchantId;
	private String charset;
	private String language;
	private String signType;
	private String signMsg;
	// 业务参数
	private String resultCode;
	private String errorMsg;
	private String refundOrderNo;
	private String errCode;

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

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getRefundOrderNo() {
		return refundOrderNo;
	}

	public void setRefundOrderNo(String refundOrderNo) {
		this.refundOrderNo = refundOrderNo;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	@Override
	public String toString() {
		return "RefundResDTO [version=" + version + ", merchantId=" + merchantId + ", charset=" + charset
				+ ", language=" + language + ", signType=" + signType + ", signMsg=" + signMsg + ", resultCode="
				+ resultCode + ", errorMsg=" + errorMsg + ", refundOrderNo=" + refundOrderNo + "]";
	}

	public String signatureData(String key) {
		return "version=" + version + "&merchantId=" + merchantId + "&charset=" + charset + "&language=" + language
				+ "&signType=" + signType + "&resultCode=" + resultCode + "&refundOrderNo=" + refundOrderNo + "&key="+ key;
	}

}
