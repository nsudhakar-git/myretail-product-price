package com.myretail.products;

public class Price {
	
	public Price(){
		
	}
	
	public Price(double amount, String currencycode) {
		this.currencycode = currencycode;
		this.amount = amount;
	}

	public String getCurrencycode() {
		return currencycode;
	}
	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	String currencycode;
	double amount;
}
