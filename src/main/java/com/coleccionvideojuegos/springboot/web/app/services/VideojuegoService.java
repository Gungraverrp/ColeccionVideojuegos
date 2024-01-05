package com.coleccionvideojuegos.springboot.web.app.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.coleccionvideojuegos.springboot.web.app.dao.VideojuegoDao;
import com.coleccionvideojuegos.springboot.web.app.data.Formato;
import com.coleccionvideojuegos.springboot.web.app.data.Genero;
import com.coleccionvideojuegos.springboot.web.app.data.Plataforma;
import com.coleccionvideojuegos.springboot.web.app.data.Videojuego;

public class VideojuegoService {

	/**
	 * DEVUELVE EL LISTADO CON LOS VIDEOJUEGOS FILTRADOS
	 * @param idPlataforma
	 * @param idGenero
	 * @param idFormato
	 * @param nombre
	 * @return
	 */
	public static List<Videojuego> getVideojuegos(String idPlataforma, String idGenero, String idFormato, String nombre) {
		    try {
		      final List<Videojuego> lstVideojuegos = VideojuegoDao.getVideojuegos(idPlataforma, idGenero, idFormato, nombre);
		      return lstVideojuegos;
		    } catch (Exception e) {
		      log.error("Error al obtener el listado de videojuegos: ", e);
		      return null;
		    }
	}
	 
	
	/**
	 * GUARDAR UN VIDEOJUEGO
	 * @param operacion
	 * @param videojuego
	 * @return
	 */
	public static Videojuego guardarVideojuego(String operacion, Videojuego videojuego) {
	    try {
	      final Videojuego v = VideojuegoDao.guardarVideojuego(operacion, videojuego);
	      return v;
	    } catch (Exception e) {
	      log.error("Error al guardar el videojuego: ", e);
	      return null;
	    }
	}
 
	
	/**
	 * BORRA UN VIDEOJUEGO
	 * @param idVideojuego
	 * @return
	 */
	public static int borrarVideojuego(String idVideojuego) {
	    try {
	      final int resultado = VideojuegoDao.borrarVideojuego(idVideojuego);
	      return resultado;
	    } catch (Exception e) {
	      log.error("Error al borrar el videojuego: ", e);
	      return 0;
	    }
	}
	
	/**
	 * DEVUELVE UN VIDEOJUEGO POR SU ID
	 * @param idVideojuego
	 * @return
	 */
	public static Videojuego getVideojuego(String idVideojuego) {
	    try {
	      final Videojuego videojuego = VideojuegoDao.getVideojuego(idVideojuego);
	      return videojuego;
	    } catch (Exception e) {
	      log.error("Error al obtener el videojuego: ", e);
	      return null;
	    }
	}
	
	/**
	 * DEVUELVE TODAS LAS PLATAFORMAS REGISTRADAS EN LA BD
	 * @return
	 */
	public static List<Plataforma> getPlataformas() {
	    try {
	      final List<Plataforma> lst = VideojuegoDao.getPlataformas();
	      return lst;
	    } catch (Exception e) {
	      log.error("Error al obtener el listado de plataformas: ", e);
	      return null;
	    }
	}
	
	
	/**
	 * DEVUELVE TODAS LOS GENEROS REGISTRADOS EN LA BD
	 * @return
	 */
	public static List<Genero> getGeneros() {
	    try {
	      final List<Genero> lst = VideojuegoDao.getGeneros();
	      return lst;
	    } catch (Exception e) {
	      log.error("Error al obtener el listado de generos: ", e);
	      return null;
	    }
	}
	
	
	
	
	
	/**
	 * DEVUELVE TODAS LOS FORMATOS REGISTRADOS EN LA BD
	 * @return
	 */
	public static List<Formato> getFormatos() {
	    try {
	      final List<Formato> lst = VideojuegoDao.getFormatos();
	      return lst;
	    } catch (Exception e) {
	      log.error("Error al obtener el listado de formatos: ", e);
	      return null;
	    }
	}
	
	
	public final static Logger log = LogManager.getLogger(VideojuegoService.class);
}
