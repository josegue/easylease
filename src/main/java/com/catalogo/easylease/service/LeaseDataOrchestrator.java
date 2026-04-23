package com.catalogo.easylease.service;

import com.catalogo.easylease.model.*;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeaseDataOrchestrator {

    private final FtpIntegrationService ftpService = new FtpIntegrationService();
    private final ExcelParserService excelParser = new ExcelParserService();
    private final JsGeneratorService jsGenerator = new JsGeneratorService();

    @Value("${ftp.incoming-dir}") private String remoteIncomingDir;
    @Value("${ftp.public-dir}") private String remotePublicDir;

    public void procesarFlujoCatalogo() {
        FTPClient ftpClient = null;
        try {
            ftpClient = ftpService.connect();
            String[] nombresArchivos = ftpClient.listNames(remoteIncomingDir);

            if (nombresArchivos == null || nombresArchivos.length == 0) {
            	System.out.println("No se encontraron archivos en el directorio remoto.");
                return;
            }

            for (String nombreArchivo : nombresArchivos) {
                if (isValidExcelFile(nombreArchivo)) {
                    procesarArchivo(ftpClient, nombreArchivo);
                }
            }

        } catch (Exception e) {
        	System.err.println("Fallo crítico en el proceso de lectura FTP" + e);
        } finally {
            ftpService.disconnect(ftpClient);
        }
    }

    private void procesarArchivo(FTPClient ftpClient, String fullFileName) {
    	System.out.println("Iniciando procesamiento del fichero: " + fullFileName);
        StringBuilder processLog = new StringBuilder("Proceso ejecutado: " + LocalDateTime.now() + "\n");
        
        List<Product> productos = new ArrayList<>();
        List<Legal> legals = new ArrayList<>();
        List<ProductPB> productosPB = new ArrayList<>();
        List<LegalPB> legalsPB = new ArrayList<>();
        List<ProductRV> productosRV = new ArrayList<>();
        List<LegalRV> legalsRV = new ArrayList<>();

        String marca = extractMarcaFromFileName(fullFileName);
        String baseName = extractBaseName(fullFileName);

        try (InputStream is = ftpClient.retrieveFileStream(fullFileName);
             Workbook workbook = new XSSFWorkbook(is)) {
            
            ftpClient.completePendingCommand();
            Sheet sheetFlyer = findSheet(workbook, "Flyer");

            int indexLegalFlyer = 1;
            for (Row row : sheetFlyer) {
                if (row.getRowNum() < 2) continue; // Saltar cabeceras
                
                Product product = excelParser.getProducto(row, String.valueOf(indexLegalFlyer));
                if(product==null) break;
                productos.add(product);
                legals.add(excelParser.getLegal(row, String.valueOf(indexLegalFlyer), marca));
                
                if (indexLegalFlyer == 10) break; 
                indexLegalFlyer++;
            }
            
            Sheet sheetFlyerSinEntrada = findSheet(workbook, "Flyer_sin_entrada");

            int indexLegalFlyerSinEntrada = 1;
            for (Row row : sheetFlyerSinEntrada) {
                if (row.getRowNum() < 2) continue; // Saltar cabeceras

                ProductPB product = excelParser.getProductoPB(row, String.valueOf(indexLegalFlyerSinEntrada));
                if(product==null) break;
                
                productosPB.add(product);
                legalsPB.add(excelParser.getLegalPB(row, String.valueOf(indexLegalFlyerSinEntrada), marca));
                
                if (indexLegalFlyerSinEntrada == 10) break; 
                indexLegalFlyerSinEntrada++;
            }
            
            Sheet sheetRenovador = findSheet(workbook, "Flyer_renovador");

            int indexLegalRenovador = 1;
            for (Row row : sheetRenovador) {
                if (row.getRowNum() < 2) continue; // Saltar cabeceras

                ProductRV product = excelParser.getProductoRV(row, String.valueOf(indexLegalRenovador));
                if(product==null) break;
                
                productosRV.add(product);
                
                legalsRV.add(excelParser.getLegalRV(row, String.valueOf(indexLegalRenovador), marca));
                
                if (indexLegalRenovador == 10) break; 
                indexLegalRenovador++;
            }
            

            // Subida de archivos (Standard)
            if (!productos.isEmpty()) {
                subirArchivosJs(ftpClient, baseName + "_PRE/js/products.js", productos, legals, processLog);
                // Subida de archivos (Pack Business)
                if (!productosPB.isEmpty()) {
                    subirArchivosJs(ftpClient, baseName + "-packbusiness_PRE/js/products_PB.js", productosPB, legalsPB, processLog);
                    // Subida de archivos (Pack RENOVADOR)
                    if (!productosRV.isEmpty()) {
                        subirArchivosJs(ftpClient, baseName + "-renovadores_PRE/js/products_RN.js", productosRV, legalsRV, processLog);
                    }
                }
            }



        } catch (Exception e) {
        	System.err.println("Error procesando fichero " + fullFileName + " "+ e);
            processLog.append("Error procesando fichero: ").append(e.getMessage());
        }

        guardarLogEjecucion(ftpClient, baseName, processLog.toString());
    }

    private void subirArchivosJs(FTPClient ftpClient, String suffixPath, List<?> prods, List<?> legs, StringBuilder processLog) {
        String remotePath = remotePublicDir + suffixPath;
        try (InputStream jsStream = jsGenerator.generateJsContent(prods, legs)) {
            if (ftpService.uploadFile(ftpClient, jsStream, remotePath)) {
            	System.out.println("Archivo JSON/JS guardado en: " + remotePath);
                processLog.append("Archivo guardado exitosamente en: ").append(remotePath).append("\n");
            } else {
            	System.err.println("Error al guardar en el FTP: " + remotePath);
            }
        } catch (Exception e) {
        	System.err.println("Error generando JS para: " + remotePath + " " + e);
        }
    }

    private void guardarLogEjecucion(FTPClient ftpClient, String baseName, String logContent) {
        String logFileName = remotePublicDir + baseName + "_PRE/js/" + baseName + 
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss")) + ".log";
        
        try (InputStream logStream = new ByteArrayInputStream(logContent.getBytes())) {
            ftpService.uploadFile(ftpClient, logStream, logFileName);
        } catch (Exception e) {
        	System.err.println("Error subiendo el archivo de log: " + logFileName + " " + e);
        }
    }

    private Sheet findSheet(Workbook workbook, String name) {
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            if (name.equalsIgnoreCase(workbook.getSheetName(i))) {
                return workbook.getSheetAt(i);
            }
        }
        return workbook.getSheetAt(0); // Fallback a la primera hoja
    }

    private boolean isValidExcelFile(String fileName) {
        return fileName != null && fileName.endsWith(".xlsx") && fileName.length() > 21;
    }

    private String extractMarcaFromFileName(String fileName) {
        try {
            String marca = fileName.substring(21, fileName.indexOf(".xlsx"));
            return marca.substring(0, marca.indexOf("_")).toLowerCase();
        } catch (Exception e) {
            return "marca_default";
        }
    }

    private String extractBaseName(String fileName) {
        return extractMarcaFromFileName(fileName);
    }
}
