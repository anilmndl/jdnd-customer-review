package com.udacity.course3.reviews.mongoRepository;

import com.udacity.course3.reviews.document.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoReviewRepository extends MongoRepository<Review, String> {

    List<Review> findReviewsByProductId(int productId);
}
