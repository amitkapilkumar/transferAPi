package com.transfer.controller;

import javax.security.auth.login.AccountNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.transfer.dto.AccountTransfer;
import com.transfer.dto.BalanceCheck;
import com.transfer.dto.TransferStatus;
import com.transfer.exception.InvalidTransferException;
import com.transfer.service.AccountService;
import com.transfer.service.AccountServiceImpl;

@Path("/account")
public class AccountController {
	private AccountService accountService;
	
	public AccountController() {
		accountService = new AccountServiceImpl();
	}
	
	@POST
	@Path("/transfer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TransferStatus transfer(AccountTransfer accountTransfer) {
		TransferStatus status = new TransferStatus();
		try {
			accountService.transfer(accountTransfer);
			status.setMessage("Money Transfered");
		} catch (AccountNotFoundException | InvalidTransferException e) {
			status.setMessage(e.getMessage());
		}
		return status;
	}
	
	@GET
	@Path("/balance/{sortcode}/{acccountno}")
	@Produces(MediaType.APPLICATION_JSON)
	public BalanceCheck getBalance(@PathParam("sortcode") String sortCode, @PathParam("accountno") String accountNo) throws AccountNotFoundException {
		BalanceCheck check = new BalanceCheck();
		double balance = accountService.getBalance(sortCode, accountNo);
		check.setAccountNo(accountNo);
		check.setSortCode(sortCode);
		check.setBalance(balance);
		return check;
	}
	
	@PUT
	@Path("/add/{sortcode}/{acccountno}")
	public Response addAccount(@PathParam("sortcode") String sortCode, @PathParam("accountno") String accountNo) {
		accountService.addAccount(sortCode, accountNo);
		return Response.status(200).build();
	}
}
