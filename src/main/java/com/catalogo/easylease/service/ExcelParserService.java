package com.catalogo.easylease.service;

import com.catalogo.easylease.enums.ExcelColumn;
import com.catalogo.easylease.model.*;
import com.catalogo.easylease.utils.DataFormatterUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

@Service
public class ExcelParserService {

    public Product getProducto(Row row, String numeroLegal) {
        if (isRowEmpty(row)) return null;

        Product producto = new Product();
        String modeloRaw = getCell(row, ExcelColumn.MODELO);
        
        producto.setQuery("coches");
        producto.setModelo(DataFormatterUtils.formatoNumeroSinDecimales(modeloRaw));
        producto.setAlt(producto.getModelo()); // Reutilizamos el valor ya limpio
        producto.setAcabado(getCell(row, ExcelColumn.ACABADO));
        producto.setNumerolegal(numeroLegal);
        producto.setCuotaMensualSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.CUOTA_MENSUAL_SIN_IVA)));
        producto.setFianza(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.FIANZA)));
        
	    producto.setTin(DataFormatterUtils.formatoNumeroPorcentaje(getCell(row, ExcelColumn.TIN)));
	    producto.setTae(DataFormatterUtils.formatoNumeroPorcentaje(getCell(row, ExcelColumn.TAE)));
	    producto.setUltimaCuotaSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.ULTIMA_CUOTA_SIN_IVA)));
	    
	    producto.setEnvironmental("https://easylease-stl.com/_shared/media/labels/label-nolabel.png");
	    producto.setEnvironmentalalt("");
        String valorEntrada = DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.ENTRADA_SIN_IVA));
        producto.setEntradaSinIVA(formatEntrada(valorEntrada));

        producto.setImagen(buildImagePath(getCell(row, ExcelColumn.IMAGEN), modeloRaw));
        return producto;
    }
    public ProductPB getProductoPB(Row row, String numeroLegal) {
        if (isRowEmpty(row)) return null;

        ProductPB producto = new ProductPB();
        String modeloRaw = getCell(row, ExcelColumn.MODELO);

        producto.setQuery("coches");
        producto.setImagen(buildImagePath(getCell(row, ExcelColumn.IMAGEN), modeloRaw));
        producto.setModelo(DataFormatterUtils.formatoNumeroSinDecimales(modeloRaw));
        producto.setAlt(producto.getModelo());
        producto.setAcabado(getCell(row, ExcelColumn.ACABADO));
        producto.setNumerolegal(numeroLegal);
        producto.setUltimaCuotaSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.ULTIMA_CUOTA_SIN_IVA)));
        producto.setEnvironmental("https://easylease-stl.com/_shared/media/labels/label-nolabel.png");
        producto.setEnvironmentalalt("");

        String valorEntrada = DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.ENTRADA_SIN_IVA));
        producto.setEntradaSinIVA(formatEntrada(valorEntrada));

        producto.setFianza(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.FIANZA)));
        producto.setCuotaConPackBusiness(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.CUOTA_CON_PACK_BUSINESS)));

        return producto;
    }
    public ProductRV getProductoRV(Row row, String numeroLegal) {
        if (isRowEmpty(row)) return null;

        ProductRV producto = new ProductRV();
        String modeloRaw = getCell(row, ExcelColumn.MODELO);

        producto.setQuery("coches");
        producto.setImagen(buildImagePath(getCell(row, ExcelColumn.IMAGEN), modeloRaw));
        producto.setModelo(DataFormatterUtils.formatoNumeroSinDecimales(modeloRaw));
        producto.setAlt(producto.getModelo());
        producto.setAcabado(getCell(row, ExcelColumn.ACABADO));
        producto.setNumerolegal(numeroLegal);
        producto.setUltimaCuotaSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.ULTIMA_CUOTA_SIN_IVA)));
        producto.setEnvironmental("https://easylease-stl.com/_shared/media/labels/label-nolabel.png");
        producto.setEnvironmentalalt("");

        String valorEntrada = DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.ENTRADA_SIN_IVA));
        producto.setEntradaSinIVA(formatEntrada(valorEntrada));

        producto.setFianza(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.FIANZA)));
        producto.setCuotaConPackBusiness(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.CUOTA_CON_PACK_BUSINESS)));

        return producto;
    }
    public Legal getLegal(Row row, String numeroLegal, String marca) {
        if (isRowEmpty(row)) return null;

        Legal legal = new Legal();
        legal.setNumerolegal(numeroLegal);
        legal.setModelo(DataFormatterUtils.formatoNumeroSinDecimales(getCell(row, ExcelColumn.MODELO)));
        legal.setAcabado(getCell(row, ExcelColumn.ACABADO));
        
        legal.setCuotaMensualSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.CUOTA_MENSUAL_SIN_IVA)));
        legal.setCuotaMensualConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.CUOTA_MENSUAL_CON_IVA)));
        legal.setPrecioFinanciadoConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.PRECIO_FINANCIADO_CON_IVA)));
        legal.setPrecioFinanciadoSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.PRECIO_FINANCIADO_SIN_IVA)));

        // Se reutiliza la extracción para Meses y Número de Cuotas
        String mesesCuotas = DataFormatterUtils.formatoNumeroSinDecimales(getCell(row, ExcelColumn.MESES_PLAZO_CUOTAS));
        legal.setMeses(mesesCuotas);
        legal.setNumeroCuotas(mesesCuotas);

        legal.setTin(DataFormatterUtils.formatoNumeroPorcentaje(getCell(row, ExcelColumn.TIN)));
        legal.setTae(DataFormatterUtils.formatoNumeroPorcentaje(getCell(row, ExcelColumn.TAE)));
        legal.setUltimaCuotaSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.ULTIMA_CUOTA_SIN_IVA)));
        legal.setUltimaCuotaConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.ULTIMA_CUOTA_CON_IVA)));
        legal.setInteresesSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.INTERESES_SIN_IVA)));
        legal.setInteresesConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.INTERESES_CON_IVA)));
        legal.setCosteTotalSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.COSTE_TOTAL_SIN_IVA)));
        legal.setCosteTotalConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.COSTE_TOTAL_CON_IVA)));
        
        legal.setFechaValidez1(DataFormatterUtils.formatoFecha(getCell(row, ExcelColumn.FECHA_VALIDEZ)));
        legal.setFianza(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.FIANZA)));
        legal.setConsumo(getCell(row, ExcelColumn.CONSUMO));
        
        legal.setEntradaSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.ENTRADA_SIN_IVA)));
        legal.setEntradaConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.ENTRADA_CON_IVA)));
        legal.setComisionAperturaSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.COMISION_APERTURA_SIN_IVA)));
        legal.setComisionAperturaConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.COMISION_APERTURA_CON_IVA)));
        legal.setKmsTotales(DataFormatterUtils.formatoNumeroSinDecimales(getCell(row, ExcelColumn.KMS_TOTALES)));
        
        // Asumiendo que getWebMarca se movió a DataFormatterUtils
        legal.setWebMarca(DataFormatterUtils.getWebMarca(marca));
        
        return legal;
    }

    public LegalPB getLegalPB(Row row, String numeroLegal, String marca) {
        if (isRowEmpty(row)) return null;

        LegalPB legal = new LegalPB();
        legal.setNumerolegal(numeroLegal);
        legal.setModelo(DataFormatterUtils.formatoNumeroSinDecimales(getCell(row, ExcelColumn.MODELO)));
        legal.setAcabado(getCell(row, ExcelColumn.ACABADO));
        
        if (marca != null && !marca.isEmpty()) {
            legal.setMarca(marca.substring(0, 1).toUpperCase() + marca.substring(1));
        }

        legal.setPrecioFinanciadoConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.PRECIO_FINANCIADO_CON_IVA)));
        legal.setPrecioFinanciadoSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.PRECIO_FINANCIADO_SIN_IVA)));
        legal.setTin(DataFormatterUtils.formatoNumeroPorcentaje(getCell(row, ExcelColumn.TIN)));
        legal.setTae(DataFormatterUtils.formatoNumeroPorcentaje(getCell(row, ExcelColumn.TAE)));
        legal.setUltimaCuotaSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.ULTIMA_CUOTA_SIN_IVA)));
        legal.setUltimaCuotaConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.ULTIMA_CUOTA_CON_IVA)));
        legal.setCosteTotalSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.COSTE_TOTAL_SIN_IVA)));
        legal.setCosteTotalConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.COSTE_TOTAL_CON_IVA)));
        legal.setFianza(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.FIANZA)));
        legal.setEntradaSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.ENTRADA_SIN_IVA)));
        legal.setEntradaConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.ENTRADA_CON_IVA)));
        
        // Se reutiliza la extracción para Plazo y Número de Cuotas
        String plazoMeses = DataFormatterUtils.formatoNumeroSinDecimales(getCell(row, ExcelColumn.MESES_PLAZO_CUOTAS));
        legal.setPlazo(plazoMeses);
        legal.setNumeroCuotas(plazoMeses);

        legal.setComisionApertura(DataFormatterUtils.formatoNumeroPorcentaje(getCell(row, ExcelColumn.COMISION_APERTURA_PORCENTAJE)));
        legal.setComisionAperturaSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.COMISION_APERTURA_SIN_IVA)));
        legal.setComisionAperturaConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.COMISION_APERTURA_CON_IVA)));
        legal.setWebMarca(DataFormatterUtils.getWebMarca(marca));
        legal.setConsumo(getCell(row, ExcelColumn.CONSUMO));
        
        legal.setCuotaSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.CUOTA_MENSUAL_SIN_IVA)));
        legal.setCuotaConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.CUOTA_MENSUAL_CON_IVA)));
        legal.setKmsTotales(DataFormatterUtils.formatoNumeroSinDecimales(getCell(row, ExcelColumn.KMS_TOTALES)));
        
        legal.setTotalinteresesSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.INTERESES_SIN_IVA)));
        legal.setTotalinteresesConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.INTERESES_CON_IVA)));
        legal.setFechaValidez2(DataFormatterUtils.formatoFecha(getCell(row, ExcelColumn.FECHA_VALIDEZ)));
        
        legal.setPackBusiness(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.PACK_BUSINESS_VALOR)));
        legal.setCuotaSeguroAuto(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.CUOTA_SEGURO_AUTO)));
        legal.setCuotaMantenimiento(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.CUOTA_MANTENIMIENTO)));
        legal.setCuotaGestiondeMultas(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.CUOTA_GESTION_MULTAS)));
        
        legal.setDuracionMantenimiento(DataFormatterUtils.formatoNumeroSinDecimales(getCell(row, ExcelColumn.DURACION_MANTENIMIENTO)));
        legal.setKmsMantenimiento(DataFormatterUtils.formatoNumeroSinDecimales(getCell(row, ExcelColumn.KMS_MANTENIMIENTO)));
        
        return legal;
    }

    public LegalRV getLegalRV(Row row, String numeroLegal, String marca) {
        if (isRowEmpty(row)) return null;

        LegalRV legal = new LegalRV();
        legal.setNumerolegal(numeroLegal);
        legal.setModelo(DataFormatterUtils.formatoNumeroSinDecimales(getCell(row, ExcelColumn.MODELO)));
        legal.setAcabado(getCell(row, ExcelColumn.ACABADO));
        
        if (marca != null && !marca.isEmpty()) {
            legal.setMarca(marca.substring(0, 1).toUpperCase() + marca.substring(1));
        }

        legal.setPrecioFinanciadoConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.PRECIO_FINANCIADO_CON_IVA)));
        legal.setPrecioFinanciadoSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.PRECIO_FINANCIADO_SIN_IVA)));
        legal.setTin(DataFormatterUtils.formatoNumeroPorcentaje(getCell(row, ExcelColumn.TIN)));
        legal.setTae(DataFormatterUtils.formatoNumeroPorcentaje(getCell(row, ExcelColumn.TAE)));
        legal.setUltimaCuotaSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.ULTIMA_CUOTA_SIN_IVA)));
        legal.setUltimaCuotaConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.ULTIMA_CUOTA_CON_IVA)));
        legal.setCosteTotalSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.COSTE_TOTAL_SIN_IVA)));
        legal.setCosteTotalConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.COSTE_TOTAL_CON_IVA)));
        legal.setFianza(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.FIANZA)));
        legal.setEntradaSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.ENTRADA_SIN_IVA)));
        legal.setEntradaConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.ENTRADA_CON_IVA)));
        
        // Se reutiliza la extracción para Plazo y Número de Cuotas
        String plazoMeses = DataFormatterUtils.formatoNumeroSinDecimales(getCell(row, ExcelColumn.MESES_PLAZO_CUOTAS));
        legal.setPlazo(plazoMeses);
        legal.setNumeroCuotas(plazoMeses);

        legal.setComisionApertura(DataFormatterUtils.formatoNumeroPorcentaje(getCell(row, ExcelColumn.COMISION_APERTURA_PORCENTAJE)));
        legal.setComisionAperturaSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.COMISION_APERTURA_SIN_IVA)));
        legal.setComisionAperturaConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.COMISION_APERTURA_CON_IVA)));
        legal.setWebMarca(DataFormatterUtils.getWebMarca(marca));
        legal.setConsumo(getCell(row, ExcelColumn.CONSUMO));
        
        legal.setCuotaSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.CUOTA_MENSUAL_SIN_IVA)));
        legal.setCuotaConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.CUOTA_MENSUAL_CON_IVA)));
        legal.setKmsTotales(DataFormatterUtils.formatoNumeroSinDecimales(getCell(row, ExcelColumn.KMS_TOTALES)));
        
        legal.setTotalinteresesSinIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.INTERESES_SIN_IVA)));
        legal.setTotalinteresesConIVA(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.INTERESES_CON_IVA)));
        legal.setFechaValidez2(DataFormatterUtils.formatoFecha(getCell(row, ExcelColumn.FECHA_VALIDEZ)));
        
        legal.setPackBusiness(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.PACK_BUSINESS_VALOR)));
        legal.setCuotaSeguroAuto(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.CUOTA_SEGURO_AUTO)));
        legal.setCuotaMantenimiento(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.CUOTA_MANTENIMIENTO)));
        legal.setCuotaGestiondeMultas(DataFormatterUtils.formatoNumero(getCell(row, ExcelColumn.CUOTA_GESTION_MULTAS)));
        
        legal.setDuracionMantenimiento(DataFormatterUtils.formatoNumeroSinDecimales(getCell(row, ExcelColumn.DURACION_MANTENIMIENTO)));
        legal.setKmsMantenimiento(DataFormatterUtils.formatoNumeroSinDecimales(getCell(row, ExcelColumn.KMS_MANTENIMIENTO)));
        
        return legal;
    }

    public String getPackBusiness(Row row) {
        if (isRowEmpty(row)) return null;
        return DataFormatterUtils.cleanData(getCell(row, ExcelColumn.PACK_BUSINESS));
    }

    private boolean isRowEmpty(Row row) {
        return row.getCell(1) == null || DataFormatterUtils.getCellValue(row.getCell(1)).isEmpty() 
               || "0.0".equals(DataFormatterUtils.getCellValue(row.getCell(1)));
    }

    private String getCell(Row row, ExcelColumn column) {
        return DataFormatterUtils.cleanData(DataFormatterUtils.getCellValue(row.getCell(column.getIndex())));
    }

    private String formatEntrada(String valorEntrada) {
        return ("0,00".equals(valorEntrada) || "0.00".equals(valorEntrada)) 
               ? "Sin entrada" : "Entrada: " + valorEntrada + " € + IVA";
    }

    private String buildImagePath(String imagenPath, String modelo) {
        String base = "https://easylease-stl.com/_shared/media/";
        String dir = DataFormatterUtils.cleanAcentos(imagenPath).replace(" ", "_");
        String file = DataFormatterUtils.cleanAcentos(DataFormatterUtils.formatoNumeroSinDecimales(modelo)).replace(" ", "_");
        return base + dir + "/" + file + ".jpg";
    }
}