package com.core.model;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonIgnore;

public class PaymentA {

	

	@JsonIgnore
	private Long id;
	@JsonIgnore
	private String gopayOrderId;
	
	// ►beneficiary address
	private String country_code;
	private String city;
	private String full_address;
	private String postcode;
	private String state;
	
	// ►beneficiary bank_details
	private String account_name;
	private String account_number;
	private String bank_country_code;
	private String bank_name;
	private String account_routing_type1;
	private String account_routing_value1;
	private String swift_code;
	private String account_currency;
	
	// ►beneficiary
	private String entity_type;
	private String company_name;

	private String payment_amount;
	private String payment_currency;
	private String reason;
	private String reference;
	private String request_id;
	private String fee_paid_by;
	private String source_currency;
	private String payment_method;

	// resp
	private BigDecimal amount_beneficiary_receives;
	private BigDecimal amount_payer_pays;
	private String created_at;
	private String failure_reason;
	private BigDecimal fee_amount;
	private String fee_currency;
	private String last_update_at;
	private String payment_id;
	private String short_reference_id;
	private String status;
	private String underlying_conversion_id;
	
	
	
	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	
	public String getAccount_currency() {
		return account_currency;
	}

	public void setAccount_currency(String account_currency) {
		this.account_currency = account_currency;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGopayOrderId() {
		return gopayOrderId;
	}

	public void setGopayOrderId(String gopayOrderId) {
		this.gopayOrderId = gopayOrderId;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFull_address() {
		return full_address;
	}

	public void setFull_address(String full_address) {
		this.full_address = full_address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getBank_country_code() {
		return bank_country_code;
	}

	public void setBank_country_code(String bank_country_code) {
		this.bank_country_code = bank_country_code;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getAccount_routing_type1() {
		return account_routing_type1;
	}

	public void setAccount_routing_type1(String account_routing_type1) {
		this.account_routing_type1 = account_routing_type1;
	}

	public String getAccount_routing_value1() {
		return account_routing_value1;
	}

	public void setAccount_routing_value1(String account_routing_value1) {
		this.account_routing_value1 = account_routing_value1;
	}

	public String getSwift_code() {
		return swift_code;
	}

	public void setSwift_code(String swift_code) {
		this.swift_code = swift_code;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
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

	public BigDecimal getAmount_beneficiary_receives() {
		return amount_beneficiary_receives;
	}

	public void setAmount_beneficiary_receives(BigDecimal amount_beneficiary_receives) {
		this.amount_beneficiary_receives = amount_beneficiary_receives;
	}

	public BigDecimal getAmount_payer_pays() {
		return amount_payer_pays;
	}

	public void setAmount_payer_pays(BigDecimal amount_payer_pays) {
		this.amount_payer_pays = amount_payer_pays;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getFailure_reason() {
		return failure_reason;
	}

	public void setFailure_reason(String failure_reason) {
		this.failure_reason = failure_reason;
	}

	public BigDecimal getFee_amount() {
		return fee_amount;
	}

	public void setFee_amount(BigDecimal fee_amount) {
		this.fee_amount = fee_amount;
	}

	public String getFee_currency() {
		return fee_currency;
	}

	public void setFee_currency(String fee_currency) {
		this.fee_currency = fee_currency;
	}

	public String getLast_update_at() {
		return last_update_at;
	}

	public void setLast_update_at(String last_update_at) {
		this.last_update_at = last_update_at;
	}

	public String getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}

	public String getShort_reference_id() {
		return short_reference_id;
	}

	public void setShort_reference_id(String short_reference_id) {
		this.short_reference_id = short_reference_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUnderlying_conversion_id() {
		return underlying_conversion_id;
	}

	public void setUnderlying_conversion_id(String underlying_conversion_id) {
		this.underlying_conversion_id = underlying_conversion_id;
	}

	@Override
	public String toString() {
		return "PaymentA [id=" + id + ", gopayOrderId=" + gopayOrderId + ", country_code=" + country_code + ", city="
				+ city + ", full_address=" + full_address + ", postcode=" + postcode + ", state=" + state
				+ ", account_name=" + account_name + ", bank_country_code=" + bank_country_code + ", bank_name="
				+ bank_name + ", account_routing_type1=" + account_routing_type1 + ", account_routing_value1="
				+ account_routing_value1 + ", swift_code=" + swift_code + ", entity_type=" + entity_type
				+ ", company_name=" + company_name + ", payment_amount=" + payment_amount + ", payment_currency="
				+ payment_currency + ", reason=" + reason + ", reference=" + reference + ", request_id=" + request_id
				+ ", fee_paid_by=" + fee_paid_by + ", source_currency=" + source_currency
				+ ", amount_beneficiary_receives=" + amount_beneficiary_receives + ", amount_payer_pays="
				+ amount_payer_pays + ", created_at=" + created_at + ", failure_reason=" + failure_reason
				+ ", fee_amount=" + fee_amount + ", fee_currency=" + fee_currency + ", last_update_at=" + last_update_at
				+ ", payment_id=" + payment_id + ", short_reference_id=" + short_reference_id + ", status=" + status
				+ ", underlying_conversion_id=" + underlying_conversion_id + "]";
	}

}
