package com.inditex.controller;

import com.inditex.model.Price;
import com.inditex.service.PriceService;
import com.inditex.exception.PriceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/apply")
    public ResponseEntity<Price> getPrice(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date applicationDate,
            @RequestParam("productId") int productId,
            @RequestParam("brandId") int brandId) {

        // Validación de parámetros
        if (applicationDate == null || productId <= 0 || brandId <= 0) {
            // Lanzamos una excepción personalizada cuando los parámetros son inválidos
            throw new IllegalArgumentException("Los parámetros de entrada no son válidos.");
        }

        // Llamada al servicio para obtener el precio
        Optional<Price> price = priceService.getApplicablePrice(brandId, productId, applicationDate);

        // Si no se encuentra el precio, lanzamos una excepción personalizada
        if (price.isEmpty()) {
            throw new PriceNotFoundException("No se encontró un precio válido para el producto " + productId + " y la marca " + brandId + " en la fecha " + applicationDate);
        }

        // Si el precio es encontrado, se devuelve la respuesta OK
        return ResponseEntity.ok(price.get());
    }
}
