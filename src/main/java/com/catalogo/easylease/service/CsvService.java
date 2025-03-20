package com.catalogo.easylease.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.catalogo.easylease.model.Legal;
import com.catalogo.easylease.model.Product;
import com.catalogo.easylease.model.Respuesta;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CsvService {

	String line = "";
	String splitBy = ";";

	public Map<String, InputStream> listarFicheros() {

		String server = "easylease-stl.com";
		int port = 21;
		String user = "jose@easylease-stl.com";
		String password = "uV8xzaKXShMA4e94eS3d";
		Map<String, InputStream> datos = new HashMap<String, InputStream>();

		FTPClient ftpClient = new FTPClient();

		try {
			// Conectar al servidor FTP
			ftpClient.connect(server, port);
			ftpClient.login(user, password);

			// Configurar el modo de transferencia a binario
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();

			// Ruta del archivo en el servidor FTP
			String remoteFilePath = "/public_ftp/csv";
			// Ruta del archivo en tu máquina local
			String localFile = "archivo_descargado.txt";

			FTPFile[] files = ftpClient.listFiles(remoteFilePath);
			if (files != null) {
				for (FTPFile ftpFile : files) {
					if (ftpFile.getName() != null) {
						String name = ftpFile.getName();
						if ((name != null) && (name.compareToIgnoreCase(".") != 0)
								&& (name.compareToIgnoreCase("..") != 0)) {
							InputStream inputStream = ftpClient.retrieveFileStream(remoteFilePath + "/" + name);
							name = name.substring(0, name.indexOf("_"));
							name = name.toLowerCase();
							datos.put(name, inputStream);
						}
					}
				}
			}
			// Confirmar la finalización de la transferencia
			ftpClient.completePendingCommand();

			// Cerrar sesión
			ftpClient.logout();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return datos;
	}
	
	public Map<String, InputStream> cargarArchivosLocales() throws IOException {
		String rutaDirectorio = "C:/ficheros/csv";
        Map<String, InputStream> archivos = new HashMap<>();

        // Obtener la ruta del directorio
        Path directorio = Paths.get(rutaDirectorio);

        // Validar si la ruta es un directorio
        if (!Files.isDirectory(directorio)) {
            throw new IllegalArgumentException("La ruta proporcionada no es un directorio: " + rutaDirectorio);
        }

        // Recorrer los archivos del directorio
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directorio)) {
        	
            for (Path archivo : stream) {
                if (Files.isRegularFile(archivo)) {
            		Respuesta resp = new Respuesta();
            		List<Product> productos = new ArrayList<Product>();
            		List<Legal> legals = new ArrayList<Legal>();
                    String nombreArchivo = archivo.getFileName().toString();
                    
					try (FileInputStream fis = new FileInputStream(archivo.toFile());
							Workbook workbook = new XSSFWorkbook(fis)) {

						Sheet sheet = workbook.getSheetAt(4); // Primer hoja
						Integer i = 1;
						for (Row row : sheet) {
							if (row.getRowNum() == 0) continue; // Saltar cabecera
							if (row.getRowNum() == 1) continue; // Saltar cabecera
							Product producto = getProducto(row,i.toString());
							if (producto == null) break; // Termina la lectura del fichero
							productos.add(producto);
							Legal legal = getLegal(row,i.toString());
							legals.add(legal);
							if(i==10) {
								break;
							}
							i++;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
                    
        			resp.setProduct(productos);
        			resp.setLegalelist(legals);
                    
					nombreArchivo = nombreArchivo.substring(0, nombreArchivo.indexOf("_"));
					nombreArchivo = nombreArchivo.toLowerCase();
                    saveJson(nombreArchivo, productos, legals);
                    System.out.println("CsvService.cargarArchivosLocales() Procesado el fichero: " + nombreArchivo);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error al leer los archivos del directorio: " + rutaDirectorio, e);
        }

        return archivos;
	
	}

	private Product getProducto(Row row , String numeroLegal) {
		Product producto = new Product();
		if (row.getCell(1)==null || getCellValue(row.getCell(1))==null || getCellValue(row.getCell(1)).isEmpty()) {
			return null; 
		}
		
		producto.setQuery("coches");
		producto.setImagen("https://easylease-stl.com/products/" + cleanData(getCellValue(row.getCell(21)).replace(" ", "_") + "/" + cleanData(formatoNumeroSinDecimales(getCellValue(row.getCell(22))).replace(" ", "_") + ".jpg")));
		producto.setTitulo(formatoNumeroSinDecimales(cleanData(getCellValue(row.getCell(22)))));
		producto.setAlt(formatoNumeroSinDecimales(cleanData(getCellValue(row.getCell(22)))));
	    producto.setSubtitulo(cleanData(getCellValue(row.getCell(23))));
	    producto.setPrecio(formatoNumero(cleanData(getCellValue(row.getCell(24)))));
	    producto.setNumerolegal(numeroLegal);
	    producto.setTin(formatoNumeroPorcentaje(cleanData(getCellValue(row.getCell(32)))));
	    producto.setTae(formatoNumeroPorcentaje(cleanData(getCellValue(row.getCell(33)))));
	    producto.setMeses(formatoNumeroSinDecimales(cleanData(getCellValue(row.getCell(26)))));
	    producto.setKm(formatoNumeroSinDecimales(cleanData(getCellValue(row.getCell(28)))));
	    producto.setUltimacuota(formatoNumero(cleanData(getCellValue(row.getCell(34)))));
	    producto.setEnvironmental("https://easylease-stl.com/labels/label-nolabel.png");
	    producto.setEnvironmentalalt("");
	    producto.setPrimeraCuotaSinIVA(formatoNumero(cleanData(getCellValue(row.getCell(47)))));
		
		return producto;
	}
	
	private Legal getLegal(Row row, String numeroLegal) {
		Legal legal = new Legal();
		
		legal.setNumerolegal(numeroLegal);
		legal.setCuotaMensualSinIVA(formatoNumero(cleanData(getCellValue(row.getCell(24)))));//Y
		legal.setCuotaMensualConIVA(formatoNumero(cleanData(getCellValue(row.getCell(25)))));//Z
		legal.setTitulo(formatoNumeroSinDecimales(cleanData(getCellValue(row.getCell(22)))));
		legal.setSubtitulo(cleanData(getCellValue(row.getCell(23))));
		legal.setPrecioFinanciado(formatoNumero(cleanData(getCellValue(row.getCell(36)))));//AK
		legal.setMeses(formatoNumeroSinDecimales(cleanData(getCellValue(row.getCell(26)))));
		legal.setTin(formatoNumeroPorcentaje(cleanData(getCellValue(row.getCell(32)))));
		legal.setTae(formatoNumeroPorcentaje(cleanData(getCellValue(row.getCell(33)))));
		legal.setUltimaCuotaSinIVA(formatoNumero(cleanData(getCellValue(row.getCell(34)))));//AI
		legal.setUltimaCuotaConIVA(formatoNumero(cleanData(getCellValue(row.getCell(35)))));//AJ
		legal.setInteresesSinIVA(formatoNumero(cleanData(getCellValue(row.getCell(39)))));//AN
		legal.setInteresesConIVA(formatoNumero(cleanData(getCellValue(row.getCell(38)))));//AM
		legal.setCosteTotalSinIVA(formatoNumero(cleanData(getCellValue(row.getCell(41)))));//AP
		legal.setCosteTotalConIVA(formatoNumero(cleanData(getCellValue(row.getCell(40)))));//AO
		legal.setFechaValidezOferta(formatoFecha(cleanData(getCellValue(row.getCell(45)))));//AC
		legal.setPrecioAlContado(formatoNumero(cleanData(getCellValue(row.getCell(42)))));//AQ
		legal.setFianza(formatoNumero(cleanData(getCellValue(row.getCell(43)))));//AR
	    legal.setConsumo(cleanData(getCellValue(row.getCell(46))));//AU
	    legal.setPrimeraCuotaSinIVA(formatoNumero(cleanData(getCellValue(row.getCell(47))))); //T
	    legal.setPrimeraCuotaConIVA(formatoNumero(cleanData(getCellValue(row.getCell(19))))); //AV
	    
	    return legal;
		
	}
	
	private String formatoFecha(String data) {
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

	private static String cleanData (String data) {
		if(data == null) {
			return "";
		}
		
		data=data.replace('€', ' ');
		data=data.replace('%', ' ');
		data=data.replace('\n', '.');
		data=data.trim();
		return data;
	}
	
	private static String formatoNumero (String data) {
		if(data == null) {
			return "";
		}
		data=data.replace(',', '.');
        Double numero;
        String numeroFormateado = data;
		try {
			numero = Double.parseDouble(data);
	        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
	        symbols.setGroupingSeparator('.'); // Separador de miles: punto
	        symbols.setDecimalSeparator(',');  // Separador de decimales: coma

	        DecimalFormat formato = new DecimalFormat("#,###.00", symbols);
	        numeroFormateado = formato.format(numero);
		} catch (NumberFormatException e) {
		}
		return numeroFormateado;
	}
	
	private static String formatoNumeroPorcentaje (String data) {
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
		} catch (NumberFormatException e) {
		}
		return numeroFormateado;
	}
	
	private static String formatoNumeroSinDecimales (String data) {
		if(data == null) {
			return "";
		}
		data=data.replace(',', '.');
        // Convertir la String a un número (double o BigDecimal)
		
        double numero;
        String numeroFormateado = data;
		try {
			numero = Double.parseDouble(data);
	        
	        // Crear un objeto DecimalFormat con el patrón deseado
	        DecimalFormat formato = new DecimalFormat("#");
	        
	        // Formatear el número
	        numeroFormateado = formato.format(numero);
		} catch (NumberFormatException e) {

		}

		return numeroFormateado;
	}

	public Map<String, InputStream> descargarArchivos() throws IOException {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		String log = new String("Proceso ejecutado: " + LocalDateTime.now().format(formato));
		ObjectMapper objectMapper = new ObjectMapper();
		String server = "easylease-stl.com";
		int port = 21;
		String user = "jose@easylease-stl.com";
		String password = "uV8xzaKXShMA4e94eS3d";
		String remoteDir = "/public_ftp/incoming";
		Map<String, InputStream> archivos = new HashMap<>();
		FTPClient ftpClient = new FTPClient();

		try {
			// Conexión al servidor FTP
			ftpClient.setConnectTimeout(5000); // Evitar bloqueos largos
			ftpClient.connect(server, port);
			ftpClient.login(user, password);

			// Configuración del cliente FTP
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			

			// Listar los archivos en el directorio remoto
			String[] nombresArchivos = ftpClient.listNames(remoteDir);
			if (nombresArchivos == null || nombresArchivos.length == 0) {
				System.out.println("No se encontraron archivos en el directorio remoto.");
				return archivos;
			}

			// Descargar cada archivo y guardarlo en el Map
			for (String nombreArchivo : nombresArchivos) {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				if ((nombreArchivo != null) && (nombreArchivo.compareToIgnoreCase(".") != 0) && (nombreArchivo.compareToIgnoreCase("..") != 0)) {
					boolean success = ftpClient.retrieveFile(nombreArchivo, outputStream);
					if (success) {
						System.out.println("Procesando el fichero: " + nombreArchivo);
						log = "\n" + "Procesando el fichero: " + nombreArchivo;
	            		Respuesta resp = new Respuesta();
	            		List<Product> productos = new ArrayList<Product>();
	            		List<Legal> legals = new ArrayList<Legal>();
						try (InputStream is =  ftpClient.retrieveFileStream(nombreArchivo);
								Workbook workbook = new XSSFWorkbook(is)) {
							ftpClient.completePendingCommand(); // Importante para finalizar correctamente la transferencia
							Sheet sheet = workbook.getSheetAt(4); // Primer hoja
							Integer i = 1;
							for (Row row : sheet) {
								if (row.getRowNum() == 0) continue; // Saltar cabecera
								if (row.getRowNum() == 1) continue; // Saltar cabecera
								Product producto = getProducto(row,i.toString());
								if (producto == null) break; // Termina la lectura del fichero
								productos.add(producto);
								Legal legal = getLegal(row,i.toString());
								legals.add(legal);
								if(i==10) {
									break;
								}
								i++;
							}
						} catch (IOException e) {
							log = "\n" + "Error: " + e.toString();
							e.printStackTrace();
						}
	                    
	        			resp.setProduct(productos);
	        			resp.setLegalelist(legals);
	        			nombreArchivo = nombreArchivo.substring(21,nombreArchivo.indexOf(".xlsx"));
						nombreArchivo = nombreArchivo.substring(0, nombreArchivo.indexOf("_"));
						nombreArchivo = nombreArchivo.toLowerCase();
	                    
			            // Convertir el VO a JSON
						String jsonContentProducts = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(productos);
						jsonContentProducts = jsonContentProducts.replaceAll("\\[","const product = \\[");
						jsonContentProducts = jsonContentProducts.replaceAll("\\]","\\];");
						String jsonContentLegals = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(legals);
						jsonContentLegals = jsonContentLegals.replaceAll("\\[","const legalelist = \\[");
						jsonContentLegals = jsonContentLegals.replaceAll("\\]","\\];");
						String result = jsonContentProducts + "\n" + jsonContentLegals;

						InputStream inputStream = new ByteArrayInputStream(result.getBytes());

			            // Subir el archivo JSON al directorio FTP
			            String remoteFilePath = "/public_html/" + nombreArchivo + "_PRE/js/products.js";
			            success = ftpClient.storeFile(remoteFilePath, inputStream);

			            if (success) {
			            	log = "\n" + "Archivo JSON guardado exitosamente en: " + remoteFilePath;
			                System.out.println("Archivo JSON guardado exitosamente en: " + remoteFilePath);
			            } else {
			            	log = "\n" + "Error al guardar el archivo JSON." + remoteFilePath;
			                System.out.println("Error al guardar el archivo JSON.");
			            }

			            inputStream.close();
	                    
	                    System.out.println("CsvService.cargarArchivosLocales() Procesado el fichero: " + nombreArchivo);
					} else {
						log = "\n" + "No se pudo descargar el archivo: " + nombreArchivo;
						System.err.println("No se pudo descargar el archivo: " + nombreArchivo);
					}
					InputStream inputStreamLog = new ByteArrayInputStream(log.getBytes());

		            // Subir el archivo JSON al directorio FTP
		            String remoteFilePathLog = "/public_html/" + nombreArchivo + "_PRE/js/" + nombreArchivo + LocalDateTime.now().format(formato) + ".log";
		            boolean successFtp = ftpClient.storeFile(remoteFilePathLog, inputStreamLog);
		            if (successFtp) {
		                System.out.println("Archivo LOG guardado exitosamente en: " + nombreArchivo);
		            } else {
		                System.out.println("Error al guardar el archivo LOG." + nombreArchivo);
		            }
		            inputStreamLog.close();		            
				}
			}

			// Cerrar sesión
			ftpClient.logout();
		} finally {
			// Desconectar del servidor
			if (ftpClient.isConnected()) {
				ftpClient.disconnect();
			}
		}

		return archivos;
	}

	public boolean saveJsonFTP(String name, Respuesta resp) {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			// Convertir el VO a JSON y guardarlo en el archivo
//			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("C://ficheros//" + name + "//js//products.js"), resp);
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("C://ficheros//js//products" + name + ".js"), resp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean saveJson(String name, List<Product> productos, List<Legal> legals) {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			// Convertir el VO a JSON y guardarlo en el archivo
//			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("C://ficheros//" + name + "//js//products.js"), resp);
//			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("C://ficheros//js//products" + name + ".js"), resp);
			String jsonContentProducts = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(productos);
			jsonContentProducts = jsonContentProducts.replaceAll("\\[","const product = \\[");
			jsonContentProducts = jsonContentProducts.replaceAll("\\]","\\];");
			String jsonContentLegals = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(legals);
			jsonContentLegals = jsonContentLegals.replaceAll("\\[","const legalelist = \\[");
			jsonContentLegals = jsonContentLegals.replaceAll("\\]","\\];");
			String result = jsonContentProducts + "\n" + jsonContentLegals;
			Files.write(Paths.get("C://ficheros//js//products" + name + ".js"), result.getBytes());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
    private static String getCellValue(Cell cell) {
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
}