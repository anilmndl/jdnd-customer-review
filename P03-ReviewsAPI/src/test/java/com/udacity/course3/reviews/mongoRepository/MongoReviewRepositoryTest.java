package com.udacity.course3.reviews.mongoRepository;

import com.udacity.course3.reviews.document.Review;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataMongoTest
@ExtendWith(SpringExtension.class)
public class MongoReviewRepositoryTest {

    @Autowired
    private MongoReviewRepository mongoReviewRepository;

    @DisplayName("given object to save"
            + " when save object using MongoDB template"
            + " then object is saved")
    @Test
    public void create_new_review() {
        // given
        Review review = new Review();
        review.setProductId(1);
        review.setReviewDetail("test review");
        review.setComments(Arrays.asList("test comment 1", "test comment 2"));
        mongoReviewRepository.save(review);

        Optional<Review> optionalReview = mongoReviewRepository.findById(review.getId());
        Assert.assertTrue(optionalReview.isPresent());
        Assert.assertEquals(optionalReview.get().getReviewDetail(),"test review");
    }

    @Test
    public void fetch_reviews_by_product_id() {
        // given
        Review review = new Review();
        review.setProductId(1);
        review.setReviewDetail("test review");
        review.setComments(Arrays.asList("test comment 1", "test comment 2"));
        mongoReviewRepository.save(review);

        List<Review> reviews = mongoReviewRepository.findReviewsByProductId(1);
        Assert.assertEquals(reviews.size(), 1);
        Assert.assertEquals(reviews.get(0).getComments().size(), 2);
    }
}
