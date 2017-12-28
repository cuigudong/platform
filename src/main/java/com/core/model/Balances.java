package com.core.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Balances implements Serializable {

	private BigDecimal available_amount;
	private String currency;
	private BigDecimal pending_amount;
	private BigDecimal total_amount;

	public BigDecimal getAvailable_amount() {
		return available_amount;
	}

	public void setAvailable_amount(BigDecimal available_amount) {
		this.available_amount = available_amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getPending_amount() {
		return pending_amount;
	}

	public void setPending_amount(BigDecimal pending_amount) {
		this.pending_amount = pending_amount;
	}

	public BigDecimal getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}

	@Override
	public String toString() {
		return "Balances [available_amount=" + available_amount + ", currency=" + currency + ", pending_amount="
				+ pending_amount + ", total_amount=" + total_amount + "]";
	}

}
