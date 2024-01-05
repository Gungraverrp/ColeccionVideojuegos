package com.coleccionvideojuegos.springboot.web.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coleccionvideojuegos.springboot.web.app.data.Formato;
import com.coleccionvideojuegos.springboot.web.app.data.Genero;
import com.coleccionvideojuegos.springboot.web.app.data.Plataforma;
import com.coleccionvideojuegos.springboot.web.app.data.Videojuego;
import com.coleccionvideojuegos.springboot.web.app.model.JsonData;
import com.coleccionvideojuegos.springboot.web.app.services.VideojuegoService;

@Controller
@EnableAutoConfiguration
public class VideojuegoController extends AbstractController {

	
	 /**
	 * DEVUELVE EL LISTADO DE LOS VIDEOJUEGOS SEGÚN LOS CAMPOS FILTRADOS	
	 * @param idPlataforma
	 * @param idGenero
	 * @param idFormato
	 * @param nombre
	 * @return
	 * @throws Exception
	 */
	 @GetMapping("/getVideojuegos")
	 @ResponseBody
	 public String getVideojuegos(@RequestParam(name = "idPlataforma") String idPlataforma,
	          @RequestParam(name = "idGenero") String idGenero,
	          @RequestParam(name = "idFormato") String idFormato,
	          @RequestParam(name = "nombre") String nombre) throws Exception {
		 log.info("Web-services URL: /getVideojuegos");
		 
		 try {
		 	List<Videojuego> lstVideojuegos = new ArrayList<>();			
		 	lstVideojuegos = VideojuegoService.getVideojuegos(idPlataforma, idGenero, idFormato, nombre);
		 	return _gson.toJson(new JsonData(lstVideojuegos, "OK"));
		 } catch (Exception e){
			 log.error("Error al devolver el listado de videojuegos", e);
			 return _gson.toJson(new JsonData(null, "ERROR"));
		 }
		 
	 }
	 
	 
	 /**
	  * OBTIENE UN VIDEOJUEGO POR SU ID
	  * @param idVideojuego
	  * @return
	  * @throws Exception
	  */
	 @CrossOrigin(origins = "*")
	 @GetMapping("/getVideojuego")
	 @ResponseBody
	 public String getVideojuego(@RequestParam(name = "idVideojuego") String idVideojuego) throws Exception {
		 log.info("Web-services URL: /getVideojuegos");
		 
		 try {
		 	Videojuego videojuego = null;			
		 	videojuego = VideojuegoService.getVideojuego(idVideojuego);
		 	return _gson.toJson(new JsonData(videojuego, "OK"));
		 } catch (Exception e){
			 log.error("Error al devolver el videojuego", e);
			 return _gson.toJson(new JsonData(null, "ERROR"));
		 }
		 
	 }
	 
	 
	 /**
	  * DEVUELVE TODAS LAS PLATAFORMAS REGISTRADAS EN LA BD
	  * @return
	  */
	 @GetMapping("/getPlataformas")
	 @ResponseBody
	 public String getPlataformas() throws Exception {
		 log.info("Web-services URL: /getPlataformas");
		 
		 try {
		 	List<Plataforma> lst = new ArrayList<>();
		 	lst = VideojuegoService.getPlataformas();
		 	return _gson.toJson(new JsonData(lst, "OK"));
		 } catch (Exception e){
			 log.error("Error al devolver el listado de plataformas", e);
			 return _gson.toJson(new JsonData(null, "ERROR"));
		 }
		 
	 }
	 
	 /**
	  * DEVUELVE TODAS LAS PLATAFORMAS REGISTRADAS EN LA BD
	  * @return
	  */
	 @GetMapping("/getGeneros")
	 @ResponseBody
	 public String getGeneros() throws Exception {
		 log.info("Web-services URL: /getGeneros");
		 
		 try {
		 	List<Genero> lst = new ArrayList<>();
		 	lst = VideojuegoService.getGeneros();
		 	return _gson.toJson(new JsonData(lst, "OK"));
		 } catch (Exception e){
			 log.error("Error al devolver el listado de generos", e);
			 return _gson.toJson(new JsonData(null, "ERROR"));
		 }
		 
	 }
	 
	 
	 
	 /**
	  * DEVUELVE TODAS LAS FORMATOS REGISTRADAS EN LA BD
	  * @return
	  */
	 @GetMapping("/getFormatos")
	 @ResponseBody
	 public String getFormatos() throws Exception {
		 log.info("Web-services URL: /getFormatos");
		 
		 try {
		 	List<Formato> lst = new ArrayList<>();
		 	lst = VideojuegoService.getFormatos();
		 	return _gson.toJson(new JsonData(lst, "OK"));
		 } catch (Exception e){
			 log.error("Error al devolver el listado de formatos", e);
			 return _gson.toJson(new JsonData(null, "ERROR"));
		 }
		 
	 }
	 
	 
	 	 
	 
	 
	 
	/**
	 * GUARDAR UN VIDEOJUEGO
	 * @param operacion
	 * @param content
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/guardarVideojuego" }, method = RequestMethod.POST)
	@ResponseBody
	public String guardarVideojuego(@RequestParam(name = "operacion") String operacion,
	      @RequestBody final String content) throws Exception {
	    log.info("Web-services URL: /guardarVideojuego");
	    
	    try {
	    	Videojuego videojuego = _gson.fromJson(content, Videojuego.class);	         
	    	videojuego = VideojuegoService.guardarVideojuego(operacion, videojuego);	
	    	if (videojuego != null) {
	    		return _gson.toJson(new JsonData(videojuego, "OK"));
	    	} else {
	    		return _gson.toJson(new JsonData(null, "Error: Hay algún error.\nNo se ha podido guardar el videojuego."));
	    	}
	    } catch (Exception e) {
	        log.error("Error al guardar un videojuego: ", e);
	        return _gson.toJson(new JsonData("", "Error: Error al modificar el videojuego"));
	    }
	}
	  
	
	
	/** 
	 * BORRA UN VIDEOJUEGO
	 * @param idVideojuego
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/borrarVideojuego" }, method = RequestMethod.POST)
	@ResponseBody
	public String borrarVideojuego(@RequestParam(name = "idVideojuego") String idVideojuego) throws Exception {
	    log.info("Web-services URL: /borrarVideojuego");
	    
	    try {        
	    	int resultado = VideojuegoService.borrarVideojuego(idVideojuego);	        
	    	return _gson.toJson(new JsonData(resultado, "OK"));
	    } catch (Exception e) {
	        log.error("Error al borrar un videojuego: ", e);
	        return _gson.toJson(new JsonData(0, "Error: Error al borrar el videojuego"));
	    }
	}
	
	
	 
	 public final static Logger log = LogManager.getLogger(VideojuegoController.class);
}
