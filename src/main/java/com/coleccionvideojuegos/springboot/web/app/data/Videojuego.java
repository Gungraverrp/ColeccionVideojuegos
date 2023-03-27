package com.coleccionvideojuegos.springboot.web.app.data;


public class Videojuego {

	public Long id;
	public String nombre;
	public Long idPlataforma;
	public Long idFormato;
	public Long idGenero;	
	public String plataforma;
	public String formato;
	public String genero;
	
	
	
	/**
	 * CONSTRUCTOR PRINCIPAL
	 * @param id
	 * @param nombre
	 * @param idPlataforma
	 * @param idFormato
	 * @param idGenero
	 */
	public Videojuego(Long id, String nombre, Long idPlataforma, Long idGenero, Long idFormato) {
		this.id = id;
		this.nombre = nombre;
		this.idPlataforma = idPlataforma;		
		this.idGenero = idGenero;
		this.idFormato = idFormato;
		this.plataforma = "";		
		this.genero = "";
		this.formato = "";
		
	}


	/**
	 * CONSTRUCTOR COMPLETO	
	 * @param id
	 * @param nombre
	 * @param idPlataforma
	 * @param idFormato
	 * @param idGenero
	 * @param plataforma
	 * @param formato
	 * @param genero
	 */
	public Videojuego(Long id, String nombre, Long idPlataforma, Long idGenero, Long idFormato,
			String plataforma, String genero, String formato) {
		this.id = id;
		this.nombre = nombre;
		this.idPlataforma = idPlataforma;
		this.idFormato = idFormato;
		this.idGenero = idGenero;
		this.plataforma = plataforma;
		this.formato = formato;
		this.genero = genero;
	}
	
	
	

}
