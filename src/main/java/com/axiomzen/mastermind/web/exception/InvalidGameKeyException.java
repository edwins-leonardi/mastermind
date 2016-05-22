package com.axiomzen.mastermind.web.exception;

public class InvalidGameKeyException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidGameKeyException() {
		super("Invalid game key provided");
	}
}
