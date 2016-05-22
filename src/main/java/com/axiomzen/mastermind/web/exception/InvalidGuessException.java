package com.axiomzen.mastermind.web.exception;

public class InvalidGuessException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidGuessException(String code) {
		super(code + "is not a valid guess for this game");
	}
}
