package edu.udec.exception;

import java.time.LocalTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Clase que contiene los atributos basicos de un error
 * @author Titi
 *
 */
@ApiModel(description = "Clase que contiene los atributos basicos de un error")
public class ErrorWrapper {

	/**
	 * Variable que contiene la hora del error
	 */
	@ApiModelProperty(notes = "Variable que contiene la hora del error")
	private LocalTime timestamp;
	
	/**
	 * Variable que contiene el tipo de respuesta 200, 201...
	 */
	@ApiModelProperty("Variable que contiene el tipo de respuesta 200, 201...")
	private int status;
	
	/**
	 * Nombre del status
	 */
	@ApiModelProperty(notes = "Nombre del status")
	private String error;
	
	/**
	 * Mensaje del error
	 */
	@ApiModelProperty(notes = "Mensaje del error")
	private String message;
	
	/**
	 * Traza del error
	 */
	@ApiModelProperty(notes = "Traza del error")
	private String trace;
	
	/**
	 * Path
	 */
	@ApiModelProperty(notes = "Path")
	private String path;
	
	/**
	 * Constructor vacio
	 */
	public ErrorWrapper() {
		super();
	}

	/**
	 * Constructor parametros
	 * @param status
	 * @param error
	 * @param message
	 * @param trace
	 * @param path
	 */
	public ErrorWrapper(int status, String error, String message, String trace, String path) {
		super();
		this.timestamp = LocalTime.now();
		this.status = status;
		this.error = error;
		this.message = message;
		this.trace = trace;
		this.path = path;
	}

	/**
	 * Variable GET de LocalTime
	 * @return LocalTime
	 */
	public LocalTime getTimestamp() {
		return timestamp;
	}

	/**
	 * Variable SET LocalTime
	 * @param timestamp
	 */
	public void setTimestamp(LocalTime timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Variable GET status
	 * @return int
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Variable SET status
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * Variable GET error
	 * @return String
	 */
	public String getError() {
		return error;
	}

	/**
	 * Variable SET error
	 * @param error
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * Variable GET message
	 * @return String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Variable SET message
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Variable GET trace
	 * @return String
	 */
	public String getTrace() {
		return trace;
	}

	/**
	 * Variable SET trace
	 * @param trace
	 */
	public void setTrace(String trace) {
		this.trace = trace;
	}

	/**
	 * Variable GET path
	 * @return String
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Variable SET path
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}
}
