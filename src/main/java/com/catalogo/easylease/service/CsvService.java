package com.catalogo.easylease.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.catalogo.easylease.model.Legal;
import com.catalogo.easylease.model.Product;

@Service
public class CsvService {
    public List<Product> readCsvProduct(String filePath) {
        List<Product> productos = new ArrayList<Product>();
        String line = "";
        String splitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                Product producto = new Product(data[0],data[1], data[2],data[3], data[4],data[5], data[6],data[7], data[8], data[9], data[10], data[11], data[24], data[25]);
                productos.add(producto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productos;
    }
    
    public List<Legal> readCsvLegal(String filePath) {
        List<Legal> legals = new ArrayList<Legal>();
        String line = "";
        String splitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                Legal legal = new Legal(data[6],data[12], data[13],data[2], data[4],data[14], data[9],data[7], data[8], data[15], data[16], data[17], data[18],data[19], data[20], data[21], data[22], data[23]);
                legals.add(legal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return legals;
    }
}