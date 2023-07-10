package com.inditex.interview.infrastructure.controllers;

import com.inditex.interview.application.GetProducts;
import com.inditex.interview.domain.entities.Product;
import com.inditex.interview.domain.exceptions.ProductNotFoundException;
import com.inditex.interview.infrastructure.controllers.entities.ApiError;
import com.inditex.interview.infrastructure.controllers.entities.ProductDTO;
import com.inditex.interview.infrastructure.controllers.mappers.ProductControllerMap;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = "api/v1/products")
@Tag(name = "Product API", description = "API to fetch product")
public class ProductController {

    private final GetProducts getProducts;

    @Autowired
    public ProductController(GetProducts getProducts) {
        this.getProducts = getProducts;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Get products",
        description = "Fetches the products with stocks or back soon",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved products",
                content = @Content(schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "422", description = "Invalid parameters provided (Constraints not satisfied)",
                content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "400", description = "Invalid type or missing parameters",
                content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Products not found",
                content = @Content(schema = @Schema(implementation = ApiError.class))),
        })
    public List<ProductDTO> getProducts() throws ProductNotFoundException {
        List<Product> productList = getProducts.execute();
        ProductControllerMap productControllerMap = new ProductControllerMap();
        return productControllerMap.toDTOList(productList);
    }
}
