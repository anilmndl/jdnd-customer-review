package com.udacity.course3.reviews.document;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Review;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Document("reviews")
public class MongoReview {

    @Id
    private String id;

    private String reviewDetail;

    private int productId;

    private List<String> comments = new ArrayList<>();

    public MongoReview() {
    }

    // when saving reviews to SQL, we need to construct review document as well
    public MongoReview(Review review) {
        this.productId = review.getProduct().getId();
        this.reviewDetail = review.getReviewDetail();
        if (review.getComments() != null) {
            for (Comment comment : review.getComments()) {
                comments.add(comment.getCommentDetail());
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReviewDetail() {
        return reviewDetail;
    }

    public void setReviewDetail(String reviewDetail) {
        this.reviewDetail = reviewDetail;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
