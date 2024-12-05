package com.catalogo.easylease.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.easylease.model.Legal;
import com.catalogo.easylease.model.Product;
import com.catalogo.easylease.model.Respuesta;
import com.catalogo.easylease.service.CsvService;

@RestController
public class CatalogoController {

	@Autowired
	private CsvService csvService;

	@GetMapping("/catalogo/{query}")
	public Respuesta catalogo(@PathVariable String query, Model model) {
		Respuesta resp = new Respuesta();

		String filePath = "src/main/resources/coches.csv"; // Ruta del CSV
		List<Product> productos = csvService.readCsvProduct(filePath);
		List<Product> resultProducts = new ArrayList<Product>();
		for (Product product : productos) {
			if (product.getQuery().equalsIgnoreCase(query)) {
				resultProducts.add(product);
			}
		}
		resp.setProduct(resultProducts);

		List<Legal> legals = csvService.readCsvLegal(filePath);
		List<Legal> resultLegals = new ArrayList<Legal>();
		for (Legal legal : legals) {
			for (Product product : resultProducts) {
				if (product.getNumerolegal().equalsIgnoreCase(legal.getNumerolegal())) {
					resultLegals.add(legal);
				}
			}
		}
		resp.setLegalelist(resultLegals);

		return resp;
	}
}
