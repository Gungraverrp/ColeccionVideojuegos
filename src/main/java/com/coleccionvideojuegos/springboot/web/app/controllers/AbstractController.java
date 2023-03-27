package com.coleccionvideojuegos.springboot.web.app.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;


@Controller
@EnableAutoConfiguration
public abstract class AbstractController {

	/**
	 * Convierte un objeto a Json.
	 * 
	 * @param valor
	 * @return
	 */
	public String toJson(final Class<?> valor) {
		return _gson.toJson(valor);
	}

	/**
	 * Convierte un json a un objeto de una clase determinada.
	 * 
	 * @param valor
	 * @param tipoObjeto
	 * @return
	 */
	public Class<?> fromJson(String valor, final Class<?> tipoObjeto) {
		return (Class<?>) _gson.fromJson(valor, tipoObjeto);
	}

	

	protected final static Gson _gson = new Gson();

}
