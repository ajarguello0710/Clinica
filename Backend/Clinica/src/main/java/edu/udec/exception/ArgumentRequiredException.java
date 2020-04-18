package edu.udec.exception;

public class ArgumentRequiredException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ArgumentRequiredException(String mensaje) {		
		super(mensaje);
	}

}
