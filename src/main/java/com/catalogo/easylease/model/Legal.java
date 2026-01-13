package com.catalogo.easylease.model;

public class Legal {

	private String numerolegal;
	private String cuotaMensualSinIVA;//Y
	private String cuotaMensualConIVA;//Z
	private String modelo;
	private String acabado;// X
	private String kmsTotales;
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
	private String numeroCuotas;
	private String comisionAperturaSinIVA;
	private String comisionAperturaConIVA;
	private String entradaSinIVA;
//	private String entradaConIVA;


	private String fianza;//AR
    private String consumo;//AU
//    private String primeraCuotaSinIVA; //T
//    private String primeraCuotaConIVA; //AV
    private String webMarca;
	public Legal() {
	    
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
	 * @return the numeroCuotas
	 */
	public String getNumeroCuotas() {
		return numeroCuotas;
	}
	/**
	 * @param numeroCuotas the numeroCuotas to set
	 */
	public void setNumeroCuotas(String numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
	}
	/**
	 * @return the comisionAperturaSinIVA
	 */
	public String getComisionAperturaSinIVA() {
		return comisionAperturaSinIVA;
	}
	/**
	 * @param comisionAperturaSinIVA the comisionAperturaSinIVA to set
	 */
	public void setComisionAperturaSinIVA(String comisionAperturaSinIVA) {
		this.comisionAperturaSinIVA = comisionAperturaSinIVA;
	}
	/**
	 * @return the comisionAperturaConIVA
	 */
	public String getComisionAperturaConIVA() {
		return comisionAperturaConIVA;
	}
	/**
	 * @param comisionAperturaConIVA the comisionAperturaConIVA to set
	 */
	public void setComisionAperturaConIVA(String comisionAperturaConIVA) {
		this.comisionAperturaConIVA = comisionAperturaConIVA;
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
	 * @return the kmsTotales
	 */
	public String getKmsTotales() {
		return kmsTotales;
	}
	/**
	 * @param kmsTotales the kmsTotales to set
	 */
	public void setKmsTotales(String kmsTotales) {
		this.kmsTotales = kmsTotales;
	}
}
