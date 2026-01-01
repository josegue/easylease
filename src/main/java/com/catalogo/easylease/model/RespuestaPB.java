/**
 * 
 */
package com.catalogo.easylease.model;

import java.util.List;

/**
 * 
 */
public class RespuestaPB {

	private List<ProductPB> product;
	
	private List<LegalPB> legalelist;
	
	public RespuestaPB() {
	}

	public RespuestaPB(List<ProductPB> product, List<LegalPB> legalelist) {
		super();
		this.product = product;
		this.legalelist = legalelist;
	}

	/**
	 * @return the product
	 */
	public List<ProductPB> getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(List<ProductPB> product) {
		this.product = product;
	}

	/**
	 * @return the legalelist
	 */
	public List<LegalPB> getLegalelist() {
		return legalelist;
	}

	/**
	 * @param legalelist the legalelist to set
	 */
	public void setLegalelist(List<LegalPB> legalelist) {
		this.legalelist = legalelist;
	}


	
}
