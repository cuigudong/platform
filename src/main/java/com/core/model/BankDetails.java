package com.core.model;

public class BankDetails {
	private String account_name;
	private String account_number;
	private String bank_country_code;
	private String bank_name;
	private String account_routing_type1;
	private String account_routing_value1;
	private String swift_code;
	private String account_currency;

	public String getAccount_currency() {
		return account_currency;
	}

	public void setAccount_currency(String account_currency) {
		this.account_currency = account_currency;
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

}
