package com.core.model;

public class Payment {
	private Beneficiary beneficiary;
	private Payer payer;
	private String payment_amount;
	private String payment_currency;
	private String reason;
	private String reference;
	private String request_id;
	private String fee_paid_by;
	private String source_currency;
	private String payment_method;

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public Beneficiary getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(Beneficiary beneficiary) {
		this.beneficiary = beneficiary;
	}

	public Payer getPayer() {
		return payer;
	}

	public void setPayer(Payer payer) {
		this.payer = payer;
	}

	public String getPayment_amount() {
		return payment_amount;
	}

	public void setPayment_amount(String payment_amount) {
		this.payment_amount = payment_amount;
	}

	public String getPayment_currency() {
		return payment_currency;
	}

	public void setPayment_currency(String payment_currency) {
		this.payment_currency = payment_currency;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getFee_paid_by() {
		return fee_paid_by;
	}

	public void setFee_paid_by(String fee_paid_by) {
		this.fee_paid_by = fee_paid_by;
	}

	public String getSource_currency() {
		return source_currency;
	}

	public void setSource_currency(String source_currency) {
		this.source_currency = source_currency;
	}

	@Override
	public String toString() {
		return "Payment [beneficiary=" + beneficiary + ", payer=" + payer + ", payment_amount=" + payment_amount
				+ ", payment_currency=" + payment_currency + ", reason=" + reason + ", reference=" + reference
				+ ", request_id=" + request_id + ", fee_paid_by=" + fee_paid_by + ", source_currency=" + source_currency
				+ "]";
	}

	
	
}
