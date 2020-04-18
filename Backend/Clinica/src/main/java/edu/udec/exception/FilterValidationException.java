package edu.udec.exception;

public class FilterValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public FilterValidationException(String mensaje) {		
		super(mensaje);
	}

}
