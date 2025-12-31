package com.catalogo.easylease.model;

public class Product {

	private String query;// marca
	private String imagen;
	private String modelo;// W
	private String alt;// W
	private String acabado;// X
	private String cuotaMensualSinIVA;// Y
	private String numerolegal;
	private String tin;// AG
	private String tae;// AH
//    private String meses;//AA
//    private String km;//AC
	private String ultimaCuotaSinIVA;// AI
	private String environmental;
	private String environmentalalt;
//    private String primeraCuotaSinIVA; //AV
	private String entradaSinIVA; // M
	private String fianza;// AR

	public Product() {
		super();
	}

	public Product(String query, String imagen, String modelo, String alt, String acabado, String cuotaMensualSinIVA,
			String numerolegal, String tin, String tae, String meses, String km, String ultimaCuotaSinIVA,
			String environmental, String environmentalalt, String primeraCuotaSinIVA, String entradaSinIVA, String fianza) {
		super();
		this.query = query;
		this.imagen = imagen;
		this.modelo = modelo;
		this.alt = alt;
		this.acabado = acabado;
		if (cuotaMensualSinIVA != null) {
			this.cuotaMensualSinIVA = cuotaMensualSinIVA.substring(0, cuotaMensualSinIVA.indexOf(','));
		}
		this.numerolegal = numerolegal;
		this.tin = tin;
		this.tae = tae;
//		this.meses = meses;
//		this.km = km;
		this.ultimaCuotaSinIVA = ultimaCuotaSinIVA;
		this.environmental = environmental;
		this.environmentalalt = environmentalalt;
//		this.primeraCuotaSinIVA = primeraCuotaSinIVA;
		this.entradaSinIVA = entradaSinIVA;
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
	 * @return the cuotaMensualSinIVA
	 */
	public String getCuotaMensualSinIVA() {
		return cuotaMensualSinIVA;
	}

	/**
	 * @param cuotaMensualSinIVA the cuotaMensualSinIVA to set
	 */
	public void setCuotaMensualSinIVA(String cuotaMensualSinIVA) {
		this.cuotaMensualSinIVA = cuotaMensualSinIVA;
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
	 * @return the tin
	 */
	public String getTin() {
		return tin;
	}

	/**
	 * @param tin the tin to set
	 */
	public void setTin(String tin) {
		this.tin = tin;
	}

	/**
	 * @return the tae
	 */
	public String getTae() {
		return tae;
	}

	/**
	 * @param tae the tae to set
	 */
	public void setTae(String tae) {
		this.tae = tae;
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
