package com.core.model;

public class Beneficiary {
	private Address address;
	private BankDetails bank_details;
	private String entity_type;
	private String company_name;
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public BankDetails getBank_details() {
		return bank_details;
	}
	public void setBank_details(BankDetails bank_details) {
		this.bank_details = bank_details;
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
	@Override
	public String toString() {
		return "Beneficiary [address=" + address + ", bank_details=" + bank_details + ", entity_type=" + entity_type
				+ ", company_name=" + company_name + "]";
	}
	
	
}
