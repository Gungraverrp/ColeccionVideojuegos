package com.coleccionvideojuegos.springboot.web.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.coleccionvideojuegos.springboot.web.app.data.Formato;
import com.coleccionvideojuegos.springboot.web.app.data.Genero;
import com.coleccionvideojuegos.springboot.web.app.data.Plataforma;
import com.coleccionvideojuegos.springboot.web.app.data.Videojuego;
import com.coleccionvideojuegos.springboot.web.app.managers.MySQLManager;

public class VideojuegoDao {
	
	/**
	 * CARGA EL LISTADO DE VIDEOJUEGOS SEGÚN EL FILTRO 
	 * @param idPlataforma
	 * @param idGenero
	 * @param idFormato
	 * @param nombre
	 * @return
	 * @throws SQLException
	 */
	public static List<Videojuego> getVideojuegos(String idPlataforma, String idGenero, String idFormato, String nombre)
		      throws SQLException {

		    List<Videojuego> lstVideojuegos = new ArrayList<>();   
		    
		    try {

		       MySQLManager mySQL = new MySQLManager();
		       Connection conn = mySQL.conectar();
		       String SQL =  "Select V.id as id, "
			   				 + "V.nombre as nombre,"
			   				 + "V.idFormato, "
			   				 + "V.idGenero, "
			   				 + "V.idPlataforma,"
			   				 + "F.nombre as formato, "
			   				 + "G.nombre as genero, "
			   				 + "P.nombre as plataforma, "
			   				 + "P.compania as compania "
			   				 + "FROM ((videojuegos V inner join formatosVideojuegos F on F.id = V.idFormato) "
			   				 + "inner join generosVideojuegos G on G.id=V.idGenero) "
			   				 + "inner join plataformasVideojuegos P on P.id = V.idPlataforma ";
		       
		       String filtro = "";
		       if (!idPlataforma.equals("0")) {
		    	   if (filtro.equals("")) {
		    		   filtro = "WHERE V.idPlataforma = " + idPlataforma;
		    	   } else {
		    		   filtro = filtro + " AND V.idPlataforma = " + idPlataforma;
		    	   }
		       }
		      
		       if (!idGenero.equals("0")) {
		    	   if (filtro.equals("")) {
		    		   filtro = "WHERE V.idGenero = " + idGenero;
		    	   } else {
		    		   filtro = filtro + " AND V.idGenero = " + idGenero;
		    	   }
		       }
		       
		       if (!idFormato.equals("0")) {
		    	   if (filtro.equals("")) {
		    		   filtro = "WHERE V.idFormato = " + idFormato;
		    	   } else {
		    		   filtro = filtro + " AND V.idFormato = " + idFormato;
		    	   }
		       }
		       
		       
		       if (!nombre.equals("") && nombre != null) {
		    	   if (filtro.equals("")) {
		    		   filtro = "WHERE UPPER(V.nombre) LIKE '%" + nombre.toUpperCase() + "%'";
		    	   } else {
		    		   filtro = filtro + " AND UPPER(V.nombre) LIKE '%" + nombre.toUpperCase() + "%'";
		    	   }
		       }
		       
		      SQL = SQL + filtro + " ORDER BY V.nombre";
		      
		      PreparedStatement ps = conn.prepareStatement(SQL);
		      final ResultSet rs = ps.executeQuery();
		      
		      while (rs.next()) {
		    	  lstVideojuegos.add(new Videojuego(rs.getLong("id"), 
		    			  							rs.getString("nombre"), 
		    			  							new Plataforma(rs.getLong("idPlataforma"),rs.getString("plataforma"), rs.getString("compania")),
		    			  							new Genero(rs.getLong("idGenero"),rs.getString("genero")),
		    			  							new Formato(rs.getLong("idFormato"),rs.getString("formato"))
		    			  							)
		    			  			);
		        
		      }
		      
		      
		      rs.close();
		      ps.close();
		      conn.close();
		    } catch (final Exception e) {
		      log.error("Error al obtener el listado de los videojuegos ", e);
		    }
		    
		   
		    return lstVideojuegos;
	  }

	
	
	/**
	 * DEVUELVE TODAS LAS PLATAFORMAS REGISTRADAS EN LA BD
	 * @return
	 * @throws SQLException
	 */
	public static List<Plataforma> getPlataformas()
		      throws SQLException {

		    List<Plataforma> lst = new ArrayList<>();   
		    
		    try {

		       MySQLManager mySQL = new MySQLManager();
		       Connection conn = mySQL.conectar();
		       String SQL =  "Select * from PLATAFORMASVIDEOJUEGOS ORDER BY NOMBRE";
		      
		      PreparedStatement ps = conn.prepareStatement(SQL);
		      final ResultSet rs = ps.executeQuery();
		      
		      while (rs.next()) {
		    	  lst.add(new Plataforma(rs.getLong("id"), rs.getString("nombre"), rs.getString("compania")));	        
		      }
		      
		      
		      rs.close();
		      ps.close();
		      conn.close();
		    } catch (final Exception e) {
		      log.error("Error al obtener el listado de las plataformas ", e);
		    }
		    
		    return lst;
	}
	
	
	
	/**
	 * DEVUELVE TODAS LOS GENEROS REGISTRADOS EN LA BD
	 * @return
	 * @throws SQLException
	 */
	public static List<Genero> getGeneros()
		      throws SQLException {

		    List<Genero> lst = new ArrayList<>();   
		    
		    try {

		       MySQLManager mySQL = new MySQLManager();
		       Connection conn = mySQL.conectar();
		       String SQL =  "Select * from GENEROSVIDEOJUEGOS ORDER BY NOMBRE";
		      
		      PreparedStatement ps = conn.prepareStatement(SQL);
		      final ResultSet rs = ps.executeQuery();
		      
		      while (rs.next()) {
		    	  lst.add(new Genero(rs.getLong("id"), rs.getString("nombre")));	        
		      }
		      
		      
		      rs.close();
		      ps.close();
		      conn.close();
		    } catch (final Exception e) {
		      log.error("Error al obtener el listado de los generos ", e);
		    }
		    
		    return lst;
	}
	
	
	/**
	 * DEVUELVE TODAS LOS FORMATOS REGISTRADOS EN LA BD
	 * @return
	 * @throws SQLException
	 */
	public static List<Formato> getFormatos()
		      throws SQLException {

		    List<Formato> lst = new ArrayList<>();   
		    
		    try {

		       MySQLManager mySQL = new MySQLManager();
		       Connection conn = mySQL.conectar();
		       String SQL =  "Select * from FORMATOSVIDEOJUEGOS ORDER BY NOMBRE";
		      
		      PreparedStatement ps = conn.prepareStatement(SQL);
		      final ResultSet rs = ps.executeQuery();
		      
		      while (rs.next()) {
		    	  lst.add(new Formato(rs.getLong("id"), rs.getString("nombre")));	        
		      }
		      
		      
		      rs.close();
		      ps.close();
		      conn.close();
		    } catch (final Exception e) {
		      log.error("Error al obtener el listado de los formatos ", e);
		    }
		    
		    return lst;
	}
	
	
	
	
	
	
	
	/**
	 * DEVUELVE UN VIDEOJUEGO
	 * @param idVideojuego
	 * @return
	 * @throws SQLException
	 */
	public static Videojuego getVideojuego(String idVideojuego)
		      throws SQLException {

		    Videojuego videojuego = null;   
		    String titulo= "";
		    
		    try {
		       MySQLManager mySQL = new MySQLManager();
		       Connection conn = mySQL.conectar();
		       String SQL =  "Select V.id as id, "
			   				 + "V.nombre as nombre,"
			   				 + "V.idFormato, "
			   				 + "V.idGenero, "
			   				 + "V.idPlataforma,"
			   				 + "F.nombre as formato, "
			   				 + "G.nombre as genero, "
			   				 + "P.nombre as plataforma, "
			   				 + "P.compania as compania "
			   				 + "FROM ((videojuegos V inner join formatosVideojuegos F on F.id = V.idFormato) "
			   				 + "inner join generosVideojuegos G on G.id=V.idGenero) "
			   				 + "inner join plataformasVideojuegos P on P.id = V.idPlataforma "
			   				 + "WHERE V.id = " + idVideojuego;   
		      PreparedStatement ps = conn.prepareStatement(SQL);
		      final ResultSet rs = ps.executeQuery();
		      
		      if (rs.next()) {
		    	  titulo = rs.getString("nombre");
		    	  
		    	  videojuego = new Videojuego(rs.getLong("id"), 
    			  							  rs.getString("nombre"), 
    			  							  new Plataforma(rs.getLong("idPlataforma"), rs.getString("plataforma"), rs.getString("compania")),
    			  							  new Genero(rs.getLong("idGenero"), rs.getString("genero")),
    			  							  new Formato(rs.getLong("idFormato"), rs.getString("formato"))    			  							  
    			  							  );
		      }
		      
		      
		      rs.close();
		      ps.close();
		      conn.close();
		    } catch (final Exception e) {
		      log.error("Error al obtener el videojuego ", e);
		    }


		    return videojuego;
	  }
	
	
	
	
	
	
	
	/**
	 * GUARDA UN VIDEOJUEGO
	 * @param operacion
	 * @param videojuego
	 * @return
	 * @throws SQLException
	 */
	public static Videojuego guardarVideojuego (String operacion, Videojuego videojuego) throws SQLException {
	    String query;
		MySQLManager mySQL = new MySQLManager();
	    Connection conn = mySQL.conectar();
	    final Statement stmt = conn.createStatement();
	    
	    if (operacion.equals("INSERT")) {
		    query = "INSERT INTO VIDEOJUEGOS (nombre, idPlataforma, idGenero, idFormato) VALUES ('" + videojuego.nombre + "', " + videojuego.plataforma.id + ", " + videojuego.genero.id + ", " + videojuego.formato.id + ")";		    	   
	    } else {
	    	query = "UPDATE VIDEOJUEGOS SET "
	    				 + "nombre = '" + videojuego.nombre + "', "
	    				 + "idPlataforma = " + videojuego.plataforma.id + ", "
	    				 + "idGenero = " + videojuego.genero.id + ", "
	    				 + "idFormato = " + videojuego.formato.id 
	    				 + " WHERE id = " + videojuego.id;
	    }
	    
	    try{
	        stmt.executeUpdate(query);   
	        stmt.close();
	        conn.close();
	    } catch(SQLException sqle) {
	        log.error("guardarVideojuego - ERROR DE SQL: " + sqle);
	        return null;
	    }	    
	    
	    return videojuego;
	  }
	  
	
	
	/**
	 * BORRA UN VIDEOJUEGO
	 * @param idVideojuego
	 * @return
	 * @throws SQLException
	 */
	public static int borrarVideojuego (String idVideojuego) throws SQLException {
	    int resultado = 0;
		String query;
		MySQLManager mySQL = new MySQLManager();
	    Connection conn = mySQL.conectar();
	    final Statement stmt = conn.createStatement();
		query = "DELETE FROM VIDEOJUEGOS WHERE id = " + idVideojuego;		    	   
	    log.info("query: " + query);
	    
	    try{
	        stmt.executeUpdate(query);    
	        stmt.close();
	        conn.close();
	        
	        resultado = 1;
	    } catch(SQLException sqle) {
	        log.error("borrarVideojuego - ERROR DE SQL: " + sqle);
	    }	    
	    
	    return resultado;
	  }
	
	
	
	public final static Logger log = LogManager.getLogger(VideojuegoDao.class);
	
	
}
