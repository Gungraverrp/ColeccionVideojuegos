package com.coleccionvideojuegos.springboot.web.app.data;

public class Plataforma {
	public Long id;
	public String nombre;	
	public String compania;
	
	
	public Plataforma(Long id, String nombre, String compania) {
		this.id = id;
		this.nombre = nombre;
		this.compania = compania;
	}
	
	
}
