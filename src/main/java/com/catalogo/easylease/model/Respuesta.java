/**
 * 
 */
package com.catalogo.easylease.model;

import java.util.List;

/**
 * 
 */
public class Respuesta {

	private List<Product> product;
	
	private List<Legal> legalelist;
	
	public Respuesta() {
	}

	public Respuesta(List<Product> product, List<Legal> legalelist) {
		super();
		this.product = product;
		this.legalelist = legalelist;
	}

	/**
	 * @return the product
	 */
	public List<Product> getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(List<Product> product) {
		this.product = product;
	}

	/**
	 * @return the legalelist
	 */
	public List<Legal> getLegalelist() {
		return legalelist;
	}

	/**
	 * @param legalelist the legalelist to set
	 */
	public void setLegalelist(List<Legal> legalelist) {
		this.legalelist = legalelist;
	}
	
}
