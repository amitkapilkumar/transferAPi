package com.transfer.data;

import com.transfer.model.Account;

public interface InMemoryData {
	public void addAccount(String sortCode, String accountNo, double amount);
	public Account getAccount(String sortCode, String accountNo);
	public void addAmountToAccount(String sortCode, String accountNo, double amount);
	public void deductAmountFromAccount(String sortCode, String accountNo, double amount);
}
