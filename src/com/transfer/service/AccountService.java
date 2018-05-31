package com.transfer.service;

import javax.security.auth.login.AccountNotFoundException;

import com.transfer.dto.AccountTransfer;
import com.transfer.dto.TransferStatus;
import com.transfer.exception.InvalidTransferException;

public interface AccountService {
	public TransferStatus transfer(AccountTransfer accountTransfer) throws AccountNotFoundException, InvalidTransferException;
	public void addAccount(String sortCode, String accountNo);
	public double getBalance(String sortCode, String accountNo) throws AccountNotFoundException;
}