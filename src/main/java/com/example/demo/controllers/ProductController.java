package com.example.demo.controllers;

import com.example.demo.dtos.ProductDTO;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts() {
        List<ProductDTO> products = productService.readAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id) {
     ProductDTO product = productService.readProductByID(id);
     return ResponseEntity.ok(product);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct( @PathVariable Integer id, @RequestBody ProductDTO product) {
        ProductDTO productDTO = productService.updateProduct(id, product);
        return ResponseEntity.ok(productDTO);
    }

    @DeleteMapping
    public ResponseEntity<ProductDTO> deleteProduct( @PathVariable Integer id) {
        productService.deleteProductByID(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO product) {
        productService.createProduct(product);
        return ResponseEntity.ok(product);
    }
}
