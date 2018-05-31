package com.transfer.dto;

public class TransferStatus {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "TransferStatus [message=" + message + "]";
	}

}
