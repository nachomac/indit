package com.inditex.controller;

import com.inditex.model.Price;
import com.inditex.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public ResponseEntity<?> getPrice(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date applicationDate,
            @RequestParam("productId") int productId,
            @RequestParam("brandId") int brandId) {

        Optional<Price> price = priceService.getApplicablePrice(brandId, productId, applicationDate);

        return price.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
