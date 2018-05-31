package com.transfer.dto;

public class BalanceCheck {
	private String sortCode;
	private String accountNo;
	private double balance;
	
	public String getSortCode() {
		return sortCode;
	}
	
	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}
	
	public String getAccountNo() {
		return accountNo;
	}
	
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "BalanceCheck [sortCode=" + sortCode + ", accountNo=" + accountNo + ", balance=" + balance + "]";
	}
	
}
