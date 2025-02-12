package com.catalogo.easylease.model;

public class Product {

    private String query;//marca
    private String imagen;
    private String titulo;//W
    private String alt;//W
    private String subtitulo;//X
    private String precio;//Y
    private String numerolegal;
    private String tin;//AG
    private String tae;//AH
    private String meses;//AA
    private String km;//AC
    private String ultimacuota;//AI
    private String environmental;
    private String environmentalalt;
    private String primeraCuotaSinIVA; //AV



	public Product() {
		super();
	}
	public Product(String query, String imagen, String titulo, String alt, String subtitulo, String precio,
			String numerolegal, String tin, String tae, String meses, String km, String ultimacuota,
			String environmental, String environmentalalt, String primeraCuotaSinIVA) {
		super();
		this.query = query;
		this.imagen = imagen;
		this.titulo = titulo;
		this.alt = alt;
		this.subtitulo = subtitulo;
		if(precio != null) {
			this.precio = precio.substring(0,precio.indexOf(','));	
		}
		this.numerolegal = numerolegal;
		this.tin = tin;
		this.tae = tae;
		this.meses = meses;
		this.km = km;
		this.ultimacuota = ultimacuota;
		this.environmental = environmental;
		this.environmentalalt = environmentalalt;
		this.primeraCuotaSinIVA = primeraCuotaSinIVA;
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
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
	 * @return the subtitulo
	 */
	public String getSubtitulo() {
		return subtitulo;
	}
	/**
	 * @param subtitulo the subtitulo to set
	 */
	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
	/**
	 * @return the precio
	 */
	public String getPrecio() {
		return precio;
	}
	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(String precio) {
		this.precio = precio;
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
	 * @return the meses
	 */
	public String getMeses() {
		return meses;
	}
	/**
	 * @param meses the meses to set
	 */
	public void setMeses(String meses) {
		this.meses = meses;
	}
	/**
	 * @return the kmV
	 */
	public String getKm() {
		return km;
	}
	/**
	 * @param kmV the kmV to set
	 */
	public void setKm(String km) {
		this.km = km;
	}
	/**
	 * @return the ultimacuota
	 */
	public String getUltimacuota() {
		return ultimacuota;
	}
	/**
	 * @param ultimacuota the ultimacuota to set
	 */
	public void setUltimacuota(String ultimacuota) {
		this.ultimacuota = ultimacuota;
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
	 * @return the primeraCuotaConIVA
	 */
	public String getPrimeraCuotaSinIVA() {
		return primeraCuotaSinIVA;
	}
	/**
	 * @param primeraCuotaConIVA the primeraCuotaConIVA to set
	 */
	public void setPrimeraCuotaSinIVA(String primeraCuotaSinIVA) {
		this.primeraCuotaSinIVA = primeraCuotaSinIVA;
	}
	
}
