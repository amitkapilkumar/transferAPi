package com.transfer.service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.security.auth.login.AccountNotFoundException;

import com.transfer.data.InMemoryData;
import com.transfer.data.InMemoryDataImplFactory;
import com.transfer.dto.AccountTransfer;
import com.transfer.dto.TransferStatus;
import com.transfer.exception.InvalidTransferException;
import com.transfer.model.Account;

public class AccountServiceImpl implements AccountService {
	private InMemoryData inMemoryData;
	private final Lock transferLock;
	private final Lock addLock;
	
	public AccountServiceImpl() {
		inMemoryData = InMemoryDataImplFactory.getInstance();
		transferLock = new ReentrantLock();
		addLock = new ReentrantLock();
	}

	@Override
	public TransferStatus transfer(AccountTransfer accountTransfer) throws AccountNotFoundException, InvalidTransferException {
		TransferStatus status = new TransferStatus();
		Account fromAccount = inMemoryData.getAccount(accountTransfer.getFromSortCode(), accountTransfer.getFromAccountNo());
		if(fromAccount == null) {
			throw new AccountNotFoundException("Account not exists with sortCode : " + accountTransfer.getFromSortCode() 
			+ ", AccountNo : " + accountTransfer.getFromAccountNo());
		}
		
		Account toAccount = inMemoryData.getAccount(accountTransfer.getToSortCode(), accountTransfer.getToAccountNo());
		if(toAccount == null) {
			throw new AccountNotFoundException("Account not exists with sortCode : " + accountTransfer.getToSortCode() 
			+ ", AccountNo : " + accountTransfer.getToAccountNo());
		}
		
		transferLock.lock();
		try {
			if(fromAccount.getBalance() < accountTransfer.getAmount()) {
				throw new InvalidTransferException("Low balance in Account[SortCode : " + accountTransfer.getFromSortCode()
					+ ", AccountNo : " + accountTransfer.getToSortCode());
			}
			
			inMemoryData.deductAmountFromAccount(accountTransfer.getFromSortCode(), 
					accountTransfer.getFromAccountNo(), accountTransfer.getAmount());
			inMemoryData.addAmountToAccount(accountTransfer.getToSortCode(), 
					accountTransfer.getToAccountNo(), accountTransfer.getAmount());
		}
		finally {
			transferLock.unlock();
		}
		return status;
	}
	
	@Override
	public double getBalance(String sortCode, String accountNo) throws AccountNotFoundException {
		Account account = inMemoryData.getAccount(sortCode, accountNo);
		if(account == null) {
			throw new AccountNotFoundException("Account not exists with sortCode : " + sortCode + ", AccountNo : " + accountNo);
		}
		return account.getBalance();
	}
	
	@Override
	public void addAccount(String sortCode, String accountNo) {
		if(inMemoryData.getAccount(sortCode, accountNo) == null) {
			addLock.lock();
			try {
				if(inMemoryData.getAccount(sortCode, accountNo) == null) {
					inMemoryData.addAccount(sortCode, accountNo, 0);
				}
			}
			finally {
				addLock.unlock();
			}
		}
	}
}