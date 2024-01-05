package com.coleccionvideojuegos.springboot.web.app.dao;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;


public class MetacriticAPI {
	
	   /**
	    * DEVUELVE LA PUNTUACIÓN DE UN JUEGO DE LA API REST DE METACRITIC POR EL TÍTULO
	    * @param titulo
	    * @return
	    * @throws Exception
	    */
	   public static int getPuntuacion(String titulo) throws Exception {
		   
		   // Habilitar CORS 
		   
		   /*
		   ResponseBuilder resBuilder = Response.status(Status.OK);
		   resBuilder.header("Access-Control-Allow-Origin", "*"); // Permitir cualquier origen
		   resBuilder.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); // Permitir los métodos HTTP que se quieran habilitar
		   resBuilder.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, Authorization"); // Permitir las cabeceras que se quieran habilitar		   
		   resBuilder.build();
		   */
		   
	      // URL de la API de Metacritic con el título del juego como parámetro de búsqueda
	      String url = "https://api.metacritic.com/2.0/search/game?search_term=" + titulo;
	      
	      url="https://api.metacritic.com/2.0/search/game?search_term=ico";
	      //url = "http://www.metacritic.com/game/playstation-2/ico";
	      	      
	      // Conexión HTTP GET a la API
	      HttpURLConnection conexion = (HttpURLConnection) new URL(url).openConnection();
	      conexion.setRequestMethod("GET");
	      
	      // Lectura de la respuesta JSON de la API
	      BufferedReader respuesta = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
	      StringBuilder respuestaJSON = new StringBuilder();
	      String linea;
	      while ((linea = respuesta.readLine()) != null) {
	         respuestaJSON.append(linea);
	      }
	      respuesta.close();
	      
	      log.info(respuestaJSON.toString());
	            
	      // Análisis de la respuesta JSON y extracción de la puntuación
	      JSONObject objetoJSON = new JSONObject(respuestaJSON.toString());
	      int puntuacion = objetoJSON.getJSONArray("results").getJSONObject(0).getInt("metascore");
	      
	      // Retorno de la puntuación obtenida
	      return puntuacion;
	   }
	
	
	   
	   
	   /**
	    * 
	    * @param url
	    * @param parametro
	    * @return
	    * @throws Exception
	    */
	   public static int getParametroFromURL(String url, String parametro) throws Exception {		      
		      // Conexión HTTP GET a la API
		      HttpURLConnection conexion = (HttpURLConnection) new URL(url).openConnection();
		      conexion.setRequestMethod("GET");
		      
		      // Lectura de la respuesta JSON de la API
		      BufferedReader respuesta = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		      StringBuilder respuestaJSON = new StringBuilder();
		      String linea;
		      while ((linea = respuesta.readLine()) != null) {
		         respuestaJSON.append(linea);
		      }
		      respuesta.close();
		      
		      // Análisis de la respuesta JSON y extracción de la puntuación
		      JSONObject objetoJSON = new JSONObject(respuestaJSON.toString());
		      //String resultado = objetoJSON.getJSONArray("near_earth_objects").getJSONObject(0).getString(parametro);
		      int resultado = objetoJSON.getInt(parametro);
		      
		      // Retorno de la puntuación obtenida
		      return resultado;
		   }
		

	   
	   public final static Logger log = LogManager.getLogger(MetacriticAPI.class);

}
