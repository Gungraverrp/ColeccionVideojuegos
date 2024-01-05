package com.coleccionvideojuegos.springboot.web.app.controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewController {

	/**
	 * PÁGINA DE INICIO
	 * @return
	 * @throws IOException
	 */
	@GetMapping({ "/index", "/", "/home" })
    public String indexPage() {       
        return "index";
    }
	
}
