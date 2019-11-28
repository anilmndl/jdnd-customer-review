package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.document.Review;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.exception.ProductNotFoundException;
import com.udacity.course3.reviews.mongoRepository.MongoReviewRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class MongoReviewController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MongoReviewRepository mongoReviewRepository;

    /**
     * creates review with comments for a valid json payload
     * @param productId
     * @param review
     * @return
     * sample json request
     * {
     * 	"reviewDetail":"sample review detail for mongo db",
     * 	"productId": 1,
     * 	"comments":[
     * 		"sample somment detail 1",
     * 		"sample somment detail 2"
     * 		]
     * }
     */
    @RequestMapping(value = "/mongo/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForProduct(@PathVariable int productId, @Valid @RequestBody Review review){
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isPresent()){
            mongoReviewRepository.save(review);
            return new ResponseEntity<>(review, HttpStatus.OK);
        }else{
            throw new ProductNotFoundException();
        }
    }
}
