package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.exception.ProductNotFoundException;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.MongoReviewRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    // TODO: Wire JPA repositories here
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoReviewRepository reviewMongoRepository;

    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForProduct(@PathVariable("productId") Integer productId, @Valid @RequestBody Review review) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            review.setProduct(product);
            if (product.getReviews() == null) {
                product.setReviews(Arrays.asList(review));
            } else {
                List<Review> reviews = product.getReviews();
                reviews.add(review);
                product.setReviews(reviews);
            }
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        else {
            throw new ProductNotFoundException();
        }
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<?>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            List<Review> reviews = reviewRepository.findAllByProduct(product);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }

        throw new ProductNotFoundException();
    }

    @RequestMapping(value = "/reviews/products/mongo", method = RequestMethod.GET)
    public ResponseEntity<com.udacity.course3.reviews.document.Review> mongoReview(){
        com.udacity.course3.reviews.document.Review review = new com.udacity.course3.reviews.document.Review();
        review.setReviewDetail("sample mongo review");
        review.setProductId(1);
        reviewMongoRepository.save(review);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }
}