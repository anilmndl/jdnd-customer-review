package com.udacity.course3.reviews.entity;

import com.udacity.course3.reviews.document.MongoReview;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_title")
    @NotNull
    private String productTitle;

    @Column(name = "product_detail")
    private String productDetail;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @Transient
    private List<MongoReview> mongoReviews;

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<MongoReview> getMongoReviews() {
        return mongoReviews;
    }

    public void setMongoReviews(List<MongoReview> mongoReviews) {
        this.mongoReviews = mongoReviews;
    }
}
