package com.coleccionvideojuegos.springboot.web.app.data;

import com.google.gson.Gson;

/**
 * Clase abstracta para la implementación de datos
 */
public abstract class AbstractData {

  /**
   * Convierte un objeto a Json.
   * 
   * @param valor
   * @return
   */
  public String toJson (final Class<?> valor) {
    return _gson.toJson(valor);
  }

  /**
   * Convierte un json a un objeto de una clase determinada.
   * 
   * @param valor
   * @param tipoObjeto
   * @return
   */
  public Class<?> fromJson (String valor, final Class<?> tipoObjeto) {
    return (Class<?>) _gson.fromJson(valor, tipoObjeto);
  }

  // PROTECTED

  protected final static Gson _gson = new Gson();

}