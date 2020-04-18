package edu.udec.exception;


import io.swagger.annotations.ApiModel;

/**
 * Excepcion NotFoundModelException
 * @author Titi
 *
 */
@ApiModel(description = "Excepcion NotFoundModelException")
public class NotFoundModelException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 * @param mensaje
	 */
	public NotFoundModelException(String mensaje) {		
		super(mensaje);
	}

}