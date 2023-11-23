package org.jsp.springDataJpa.exception;

public class InvalidCredentialsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
    public String getMessage() {
    	return "Invalid phone or email or password";
    }

}
