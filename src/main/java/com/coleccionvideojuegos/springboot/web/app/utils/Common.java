package com.coleccionvideojuegos.springboot.web.app.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Common {

	
	/**
	 * Carga el valor de una propiedad del fichero de configuracion.
	 * 
	 * @param propName
	 * @return String
	 */
	public static String getProperty(final String propName) {
		String propValue = props.getProperty(propName);
		return propValue;
	}

	/**
	 * Carga el valor de una propiedad del fichero de configuracion, o un valor por
	 * defecto.
	 * 
	 * @param propName
	 * @param defaultValue
	 * @return String
	 */
	public static String getPropertyOrElse(final String propName, final String defaultValue) {

		return getProperty(propName) == null ? defaultValue : getProperty(propName);
	}

	// PRIVATE /////////////////////////////////////////////////////////////////////

	private final static Logger log = LogManager.getLogger(Common.class);

	private static Properties props = new Properties();

	static {
		try (final InputStream input = Common.class.getResourceAsStream("/application.properties")) {
			props.load(input);
		} catch (FileNotFoundException e) {
			log.error("Error al localizar fichero de propiedades, ", e);
		} catch (IOException e) {
			log.error("Error al leer fichero de propiedades, ", e);
		}
	}
}
