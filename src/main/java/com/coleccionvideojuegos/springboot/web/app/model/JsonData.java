package com.coleccionvideojuegos.springboot.web.app.model;

public class JsonData {

	public final Object data;

	public final String message;

	/**
	 * Constructor de la clase.
	 * 
	 * @param data
	 * @param message
	 */
	public JsonData(final Object data, final String message) {
		this.data = data;
		this.message = message;
	}


}
