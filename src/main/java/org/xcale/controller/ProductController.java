package org.xcale.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xcale.entity.Product;
import org.xcale.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product productSaved = productService.save(product);
            return new ResponseEntity<>(productSaved, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/all")
    public ResponseEntity<List<Product>> createProducts(@RequestBody List<Product> products) {
        try {
            List<Product> toReturn = productService.saveAll(products);
            return new ResponseEntity<>(toReturn, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/{id})")
    public ResponseEntity<Product> findByIdProduct(@PathVariable("id")Long idProduct) {
        Optional<Product> product = productService.findByIdProduct(idProduct);

        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
