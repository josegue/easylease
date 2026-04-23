package com.catalogo.easylease.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

public class DataFormatterUtils {

    private DataFormatterUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String cleanAcentos(String texto) {
        if (texto == null) return "";
        return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
    }

    public static String cleanData(String data) {
        if (data == null) return "";
        return data.replace('€', ' ').replace('%', ' ').replace('\n', '.').trim();
    }

    public static String formatoNumero(String data) {
        if (data == null || data.isEmpty()) return "";
        data = data.replace(',', '.');
        try {
            Double numero = Double.parseDouble(data);
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
            symbols.setGroupingSeparator('.');
            symbols.setDecimalSeparator(',');
            DecimalFormat formato = new DecimalFormat("#,###.00", symbols);
            String formated = formato.format(numero);
            return formated.equalsIgnoreCase(",00") ? "0,00" : formated;
        } catch (NumberFormatException e) {
            return data;
        }
    }

    public static String formatoNumeroSinDecimales(String data) {
        if (data == null || data.isEmpty()) return "";
        try {
            return new DecimalFormat("#").format(Double.parseDouble(data.replace(',', '.')));
        } catch (NumberFormatException e) {
            return data;
        }
    }
    
    public static String formatoFecha(String data) {
		String fechaFormateada = data;
        // Parsear la fecha usando SimpleDateFormat
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        Date fecha;
        try {
            fecha = formatoEntrada.parse(data);
            // Convertir a LocalDateTime
            LocalDateTime localDateTime = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            // Formatear con DateTimeFormatter
            DateTimeFormatter formatoSalida = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            fechaFormateada = localDateTime.format(formatoSalida);
        } catch (Exception e) {
        }
		return fechaFormateada;
	}
    
    public static String formatoNumeroPorcentaje (String data) {
		if(data == null) {
			return "";
		}
		data=data.replace(',', '.');
        Double numero;
        String numeroFormateado = data;
		try {
			numero = Double.parseDouble(data);
			DecimalFormat formato = new DecimalFormat("#.00");
	        numeroFormateado = formato.format(numero);
        	numero = numero * 100;
	        numeroFormateado = formato.format(numero);
	        numeroFormateado=numeroFormateado.replace('.', ',');
	        if((numeroFormateado.equalsIgnoreCase(",00"))||(numeroFormateado.equalsIgnoreCase(".00"))) {
	        	numeroFormateado = "0,00";
	        }
	        
		} catch (NumberFormatException e) {
		}
		return numeroFormateado;
	} 
    
    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        // Determina el tipo de celda
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                // Verifica si es una fecha
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                // Evalúa la fórmula y devuelve el resultado
                FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
                return getCellValue(evaluator.evaluateInCell(cell));
            case BLANK:
                return "";
            default:
                return "Tipo de celda no manejado";
        }
    }
    
    public static String getWebMarca(String marca) {

	    switch (marca) {
	        case "abarth":
	            return "www.abarth.es";
	        case "alfa-romeo":
	            return "www.alfaromeo.es";
	        case "citroen":
	            return "www.citroen.es";
	        case "ds":
	            return "www.dsautomobiles.es";
	        case "fiat":
	            return "www.fiat.es";
	        case "fiat-pro":
	            return "www.fiatprofessional.com/es";
	        case "jeep":
	            return "www.jeep.es";
	        case "lancia":
	            return "www.lancia.es";
	        case "leapmotor":
	            return "www.leapmotor.net";
	        case "opel":
	            return "www.opel.es";
	        case "peugeot":
	            return "www.peugeot.es";
	        default:
	            return("Marca no soportada: " + marca);
	    }
	}    
    // ... (Mantén aquí el resto de tus métodos: formatoFecha, formatoNumeroPorcentaje, getCellValue, getWebMarca)
}
