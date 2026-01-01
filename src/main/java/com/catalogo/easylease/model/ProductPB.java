package com.catalogo.easylease.model;

public class ProductPB {


	
	private String 	query;
	private String 	imagen;
	private String 	modelo;
	private String 	alt;
	private String 	acabado;
	private String 	cuotaConPackBusiness;
	private String 	numerolegal;
	private String 	environmental;
	private String 	environmentalalt;
	private String 	entradaSinIVA;
	private String 	ultimaCuotaSinIVA;
	private String 	fianza;
	

	public ProductPB() {
		super();
	}


	public ProductPB(String query, String imagen, String modelo, String alt, String acabado,
			String cuotaConPackBusiness, String numerolegal, String environmental, String environmentalalt,
			String entradaSinIVA, String ultimaCuotaSinIVA, String fianza) {
		super();
		this.query = query;
		this.imagen = imagen;
		this.modelo = modelo;
		this.alt = alt;
		this.acabado = acabado;
		this.cuotaConPackBusiness = cuotaConPackBusiness;
		this.numerolegal = numerolegal;
		this.environmental = environmental;
		this.environmentalalt = environmentalalt;
		this.entradaSinIVA = entradaSinIVA;
		this.ultimaCuotaSinIVA = ultimaCuotaSinIVA;
		this.fianza = fianza;
	}


	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}


	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}


	/**
	 * @return the imagen
	 */
	public String getImagen() {
		return imagen;
	}


	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}


	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	/**
	 * @return the alt
	 */
	public String getAlt() {
		return alt;
	}


	/**
	 * @param alt the alt to set
	 */
	public void setAlt(String alt) {
		this.alt = alt;
	}


	/**
	 * @return the acabado
	 */
	public String getAcabado() {
		return acabado;
	}


	/**
	 * @param acabado the acabado to set
	 */
	public void setAcabado(String acabado) {
		this.acabado = acabado;
	}


	/**
	 * @return the cuotaConPackBusiness
	 */
	public String getCuotaConPackBusiness() {
		return cuotaConPackBusiness;
	}


	/**
	 * @param cuotaConPackBusiness the cuotaConPackBusiness to set
	 */
	public void setCuotaConPackBusiness(String cuotaConPackBusiness) {
		this.cuotaConPackBusiness = cuotaConPackBusiness;
	}


	/**
	 * @return the numerolegal
	 */
	public String getNumerolegal() {
		return numerolegal;
	}


	/**
	 * @param numerolegal the numerolegal to set
	 */
	public void setNumerolegal(String numerolegal) {
		this.numerolegal = numerolegal;
	}


	/**
	 * @return the environmental
	 */
	public String getEnvironmental() {
		return environmental;
	}


	/**
	 * @param environmental the environmental to set
	 */
	public void setEnvironmental(String environmental) {
		this.environmental = environmental;
	}


	/**
	 * @return the environmentalalt
	 */
	public String getEnvironmentalalt() {
		return environmentalalt;
	}


	/**
	 * @param environmentalalt the environmentalalt to set
	 */
	public void setEnvironmentalalt(String environmentalalt) {
		this.environmentalalt = environmentalalt;
	}


	/**
	 * @return the entradaSinIVA
	 */
	public String getEntradaSinIVA() {
		return entradaSinIVA;
	}


	/**
	 * @param entradaSinIVA the entradaSinIVA to set
	 */
	public void setEntradaSinIVA(String entradaSinIVA) {
		this.entradaSinIVA = entradaSinIVA;
	}


	/**
	 * @return the ultimaCuotaSinIVA
	 */
	public String getUltimaCuotaSinIVA() {
		return ultimaCuotaSinIVA;
	}


	/**
	 * @param ultimaCuotaSinIVA the ultimaCuotaSinIVA to set
	 */
	public void setUltimaCuotaSinIVA(String ultimaCuotaSinIVA) {
		this.ultimaCuotaSinIVA = ultimaCuotaSinIVA;
	}


	/**
	 * @return the fianza
	 */
	public String getFianza() {
		return fianza;
	}


	/**
	 * @param fianza the fianza to set
	 */
	public void setFianza(String fianza) {
		this.fianza = fianza;
	}
}
