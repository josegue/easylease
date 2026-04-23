package com.catalogo.easylease.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class JsGeneratorService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public InputStream generateJsContent(List<?> products, List<?> legals) {
        try {
            String jsonProducts = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(products);
            String jsonLegals = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(legals);

            StringBuilder jsOutput = new StringBuilder();
            jsOutput.append("const product = ").append(jsonProducts).append(";\n");
            jsOutput.append("const legalelist = ").append(jsonLegals).append(";\n");

            return new ByteArrayInputStream(jsOutput.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Error generando contenido JS", e);
        }
    }
}
