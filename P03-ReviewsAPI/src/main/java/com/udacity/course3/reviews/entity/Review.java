package com.udacity.course3.reviews.entity;


import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String reviewDetail;
}
