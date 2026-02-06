package com.shop.grocery.api;

import com.shop.grocery.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/products")
public interface ProductAPI {
    @Operation(summary = "Create a new Product", responses = {
            @ApiResponse(description = "Product Created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
             })
    @PostMapping
    ResponseEntity<Product> createProduct(@RequestBody Product product);

    @Operation(summary = "Update a Product", responses = {
            @ApiResponse(description = "Product Created", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
    })
    @PatchMapping
    ResponseEntity<Product> updateProduct(@RequestBody Product product);

    @Operation(summary = "Get a Product by code", responses = {
            @ApiResponse(description = "Product Details", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
    })
    @GetMapping("/{code}")
    public ResponseEntity<Product> getProduct(
            @Parameter(description = "Product code")
            @PathVariable(
                    name = "code",
                    required = true)
            String code) ;
    @Operation(summary = "Get all Products", responses = {
            @ApiResponse(description = "Products List", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product[].class))),
    })
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() ;

    @DeleteMapping("/{code}")
    public ResponseEntity<Product> deleteProduct(
            @Parameter(description = "Product code")
            @PathVariable(
                    name = "code",
                    required = true)
            String code) ;
}
