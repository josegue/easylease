package com.catalogo.easylease.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.easylease.model.Respuesta;
import com.catalogo.easylease.service.CsvService;
import com.catalogo.easylease.service.LeaseDataOrchestrator;

@RestController
public class CatalogoController {

//	@Autowired
//	private CsvService csvService;
	
	@Autowired
	private LeaseDataOrchestrator leaseDataOrchestrator;
	
	@GetMapping("/easylease")
	public Respuesta catalogo(Model model) {
		
		try {
//			Map<String, InputStream> datos = csvService.descargarArchivos();
			leaseDataOrchestrator.procesarFlujoCatalogo();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Respuesta();
	}
}
