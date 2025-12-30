package com.catalogo.easylease.model;

public class Legal {

	private String numerolegal;
	private String cuotaMensualSinIVA;//Y
	private String cuotaMensualConIVA;//Z
//	private String titulo;
//	private String subtitulo;
	private String precioFinanciadoConIVA;//AK
	private String precioFinanciadoSinIVA;//AL
	private String meses;
	private String tin;
	private String tae;
	private String ultimaCuotaSinIVA;//AI
	private String ultimaCuotaConIVA;//AJ
	private String interesesSinIVA;//AN
	private String interesesConIVA;//AM
	private String costeTotalSinIVA;//AP
	private String costeTotalConIVA;//AO
	private String fechaValidez1;//AC
//	private String precioAlContado;//AQ
	private String fianza;//AR
    private String consumo;//AU
//    private String primeraCuotaSinIVA; //T
//    private String primeraCuotaConIVA; //AV
    private String webMarca;
	public Legal() {
	    
	}
	public Legal(String numerolegal, String cuotaMensualSinIVA, String cuotaMensualConIVA,
			String precioFinanciadoConIVA, String precioFinanciadoSinIVA, String meses, String tin, String tae,
			String ultimaCuotaSinIVA, String ultimaCuotaConIVA, String interesesSinIVA, String interesesConIVA,
			String costeTotalSinIVA, String costeTotalConIVA, String fechaValidez1, String fianza, String consumo,
			String webMarca) {
		super();
		this.numerolegal = numerolegal;
		this.cuotaMensualSinIVA = cuotaMensualSinIVA;
		this.cuotaMensualConIVA = cuotaMensualConIVA;
		this.precioFinanciadoConIVA = precioFinanciadoConIVA;
		this.precioFinanciadoSinIVA = precioFinanciadoSinIVA;
		this.meses = meses;
		this.tin = tin;
		this.tae = tae;
		this.ultimaCuotaSinIVA = ultimaCuotaSinIVA;
		this.ultimaCuotaConIVA = ultimaCuotaConIVA;
		this.interesesSinIVA = interesesSinIVA;
		this.interesesConIVA = interesesConIVA;
		this.costeTotalSinIVA = costeTotalSinIVA;
		this.costeTotalConIVA = costeTotalConIVA;
		this.fechaValidez1 = fechaValidez1;
		this.fianza = fianza;
		this.consumo = consumo;
		this.webMarca = webMarca;
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
	 * @return the cuotaMensualConIVA
	 */
	public String getCuotaMensualConIVA() {
		return cuotaMensualConIVA;
	}
	/**
	 * @param cuotaMensualConIVA the cuotaMensualConIVA to set
	 */
	public void setCuotaMensualConIVA(String cuotaMensualConIVA) {
		this.cuotaMensualConIVA = cuotaMensualConIVA;
	}
	/**
	 * @return the precioFinanciadoConIVA
	 */
	public String getPrecioFinanciadoConIVA() {
		return precioFinanciadoConIVA;
	}
	/**
	 * @param precioFinanciadoConIVA the precioFinanciadoConIVA to set
	 */
	public void setPrecioFinanciadoConIVA(String precioFinanciadoConIVA) {
		this.precioFinanciadoConIVA = precioFinanciadoConIVA;
	}
	/**
	 * @return the precioFinanciadoSinIVA
	 */
	public String getPrecioFinanciadoSinIVA() {
		return precioFinanciadoSinIVA;
	}
	/**
	 * @param precioFinanciadoSinIVA the precioFinanciadoSinIVA to set
	 */
	public void setPrecioFinanciadoSinIVA(String precioFinanciadoSinIVA) {
		this.precioFinanciadoSinIVA = precioFinanciadoSinIVA;
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
	 * @return the ultimaCuotaConIVA
	 */
	public String getUltimaCuotaConIVA() {
		return ultimaCuotaConIVA;
	}
	/**
	 * @param ultimaCuotaConIVA the ultimaCuotaConIVA to set
	 */
	public void setUltimaCuotaConIVA(String ultimaCuotaConIVA) {
		this.ultimaCuotaConIVA = ultimaCuotaConIVA;
	}
	/**
	 * @return the interesesSinIVA
	 */
	public String getInteresesSinIVA() {
		return interesesSinIVA;
	}
	/**
	 * @param interesesSinIVA the interesesSinIVA to set
	 */
	public void setInteresesSinIVA(String interesesSinIVA) {
		this.interesesSinIVA = interesesSinIVA;
	}
	/**
	 * @return the interesesConIVA
	 */
	public String getInteresesConIVA() {
		return interesesConIVA;
	}
	/**
	 * @param interesesConIVA the interesesConIVA to set
	 */
	public void setInteresesConIVA(String interesesConIVA) {
		this.interesesConIVA = interesesConIVA;
	}
	/**
	 * @return the costeTotalSinIVA
	 */
	public String getCosteTotalSinIVA() {
		return costeTotalSinIVA;
	}
	/**
	 * @param costeTotalSinIVA the costeTotalSinIVA to set
	 */
	public void setCosteTotalSinIVA(String costeTotalSinIVA) {
		this.costeTotalSinIVA = costeTotalSinIVA;
	}
	/**
	 * @return the costeTotalConIVA
	 */
	public String getCosteTotalConIVA() {
		return costeTotalConIVA;
	}
	/**
	 * @param costeTotalConIVA the costeTotalConIVA to set
	 */
	public void setCosteTotalConIVA(String costeTotalConIVA) {
		this.costeTotalConIVA = costeTotalConIVA;
	}
	/**
	 * @return the fechaValidez1
	 */
	public String getFechaValidez1() {
		return fechaValidez1;
	}
	/**
	 * @param fechaValidez1 the fechaValidez1 to set
	 */
	public void setFechaValidez1(String fechaValidez1) {
		this.fechaValidez1 = fechaValidez1;
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
	/**
	 * @return the consumo
	 */
	public String getConsumo() {
		return consumo;
	}
	/**
	 * @param consumo the consumo to set
	 */
	public void setConsumo(String consumo) {
		this.consumo = consumo;
	}
	/**
	 * @return the webMarca
	 */
	public String getWebMarca() {
		return webMarca;
	}
	/**
	 * @param webMarca the webMarca to set
	 */
	public void setWebMarca(String webMarca) {
		this.webMarca = webMarca;
	}

}
