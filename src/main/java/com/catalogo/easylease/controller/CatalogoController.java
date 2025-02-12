package com.catalogo.easylease.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.easylease.model.Respuesta;
import com.catalogo.easylease.service.CsvService;

@RestController
public class CatalogoController {

	@Autowired
	private CsvService csvService;

	@GetMapping("/easylease")
	public Respuesta catalogo(Model model) {
		//listo ficheros en un map con nombre y contenido
		//recorrer lis
		    //por cada uno de los ficheros genero una respuesta resp que incluye product y legal
		    //guardo en el ftp la respuesta en formato json
		
		try {
			Map<String, InputStream> datos = csvService.descargarArchivos();
//			Map<String, InputStream> datos = csvService.cargarArchivosLocales();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Respuesta();
	}
}
