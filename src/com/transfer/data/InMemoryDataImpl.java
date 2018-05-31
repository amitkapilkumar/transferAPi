package com.transfer.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.transfer.model.Account;

public class InMemoryDataImpl implements InMemoryData {
	private static Map<String, Map<String, Account>> accounts;
	
	InMemoryDataImpl() {
		accounts = new ConcurrentHashMap<String, Map<String, Account>>();
	}

	@Override
	public void addAccount(String sortCode, String accountNo, double amount) {
		Account account = new Account();
		account.setAccountNo(accountNo);
		account.setBalance(0);
		if(accounts.containsKey(sortCode)) {
			Map<String, Account> map = accounts.get(sortCode);
			map.put(accountNo, account);
		}
		else {
			Map<String, Account> map = new ConcurrentHashMap<>();
			map.put(accountNo, account);
			accounts.put(sortCode, map);
		}
	}

	@Override
	public Account getAccount(String sortCode, String accountNo) {
		if(accounts.containsKey(sortCode)) {
			Map<String, Account> map = accounts.get(sortCode);
			if(map.containsKey(accountNo)) {
				return map.get(accountNo);
			}
		}
		return null;
	}

	@Override
	public void addAmountToAccount(String sortCode, String accountNo, double amount) {
		updateAccount(sortCode, accountNo, amount);
	}

	@Override
	public void deductAmountFromAccount(String sortCode, String accountNo, double amount) {
		updateAccount(sortCode, accountNo, amount * -1);
	}
	
	private void updateAccount(String sortCode, String accountNo, double amount) {
		if(accounts.containsKey(sortCode)) {
			Map<String, Account> map = accounts.get(sortCode);
			if(map.containsKey(accountNo)) {
				Account account = map.get(accountNo);
				account.setBalance(account.getBalance() + amount);
			}
		}
	}
	
}
