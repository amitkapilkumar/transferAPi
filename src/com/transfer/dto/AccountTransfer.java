package com.transfer.dto;

public class AccountTransfer {
	private String toAccountNo;
	private String fromAccountNo;
	private String toSortCode;
	private String fromSortCode;
	private double amount;
	
	public String getToAccountNo() {
		return toAccountNo;
	}
	public void setToAccountNo(String toAccountNo) {
		this.toAccountNo = toAccountNo;
	}
	
	public String getFromAccountNo() {
		return fromAccountNo;
	}
	
	public void setFromAccountNo(String fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}
	
	public String getToSortCode() {
		return toSortCode;
	}
	
	public void setToSortCode(String toSortCode) {
		this.toSortCode = toSortCode;
	}
	
	public String getFromSortCode() {
		return fromSortCode;
	}
	
	public void setFromSortCode(String fromSortCode) {
		this.fromSortCode = fromSortCode;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
}