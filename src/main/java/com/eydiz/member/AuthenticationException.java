package com.eydiz.member;

public class AuthenticationException extends Exception{
	private static final long serialVersionUID = -9163250891452836751L;

	public AuthenticationException(String error) {
		super(error);
	}
}
