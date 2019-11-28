package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.document.Review;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.exception.ProductNotFoundException;
import com.udacity.course3.reviews.repository.MongoReviewRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with product entity.
 */
@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MongoReviewRepository mongoReviewRepository;


    /**
     * Creates a product.
     * <p>
     * 1. Accept product as argument. Use {@link RequestBody} annotation.
     * 2. Save product.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@Valid @RequestBody Product product) {
        productRepository.save(product);
    }

    /**
     * Finds a product by id.
     *
     * @param id The id of the product.
     * @return The product if found, or a 404 not found.
     */
    @RequestMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            // fetch reviews from mongodb
            List<Review> mongoReviews = mongoReviewRepository.findReviewByProductId(product.getId());
            product.setMongoReviews(mongoReviews);

            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            throw new ProductNotFoundException();
        }
    }

    /**
     * Lists all products.
     *
     * @return The list of products.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<?> listProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();
        return products;
    }
}