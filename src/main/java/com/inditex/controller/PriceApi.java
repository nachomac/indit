package com.inditex.controller;

import com.inditex.model.Price;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Optional;

@OpenAPIDefinition(info = @Info(title = "Price API", description = "API for retrieving applicable prices", version = "1.0"),
        servers = {
                @Server(url = "http://localhost:8080/", description = "Local Server"),
                @Server(url = "https://api.inditex.com/prices", description = "Production Server")
        })
public interface PriceApi {

    @Operation(summary = "Retrieve applicable price based on date, product ID, and brand ID",
            security = {@SecurityRequirement(name = "bearer")},
            tags = {"Price Retrieval"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Applicable price found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Price.class))),
            @ApiResponse(responseCode = "404", description = "No applicable price found", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/apply")
    ResponseEntity<Optional<Price>> getPrice(
            @Parameter(in = ParameterIn.QUERY, description = "Application date in format yyyy-MM-dd HH:mm:ss", required = true, schema = @Schema(type = "string", format = "date-time"))
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date applicationDate,

            @Parameter(in = ParameterIn.QUERY, description = "Product ID", required = true, schema = @Schema(type = "integer"))
            @RequestParam("productId") int productId,

            @Parameter(in = ParameterIn.QUERY, description = "Brand ID", required = true, schema = @Schema(type = "integer"))
            @RequestParam("brandId") int brandId
    );
}
