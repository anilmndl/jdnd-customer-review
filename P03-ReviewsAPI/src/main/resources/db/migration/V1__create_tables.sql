CREATE TABLE products(
  id INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id),

  product_title VARCHAR(255) NOT NULL,
  product_detail VARCHAR(10000) NULL
);

CREATE TABLE reviews(
  id INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id),

  review_detail VARCHAR(10000) NOT NULL,

  product_id int NOT NULL,
  FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE comments(
  id INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id),

  comment_detail VARCHAR(1000) NOT NULL,

  review_id int NOT NULL,
  FOREIGN KEY (review_id) REFERENCES reviews(id)
);

INSERT INTO products (product_title, product_detail)
VALUES ("Tesla Cybertron", "Angular designed truck.");

INSERT INTO reviews (review_detail, product_id)
VALUES ("The design is enticing.", 1);

INSERT INTO comments (comment_detail, review_id)
VALUES ("I am not so sure.", 1);

INSERT INTO comments (comment_detail, review_id)
VALUES ("I second that.", 1);


INSERT INTO reviews (review_detail, product_id)
VALUES ("The design is weird.", 1);

INSERT INTO comments (comment_detail, review_id)
VALUES ("Agreed.", 2);

INSERT INTO comments (comment_detail, review_id)
VALUES ("Look like a truck designed by 5 year old kid.", 2);



INSERT INTO products (product_title, product_detail)
VALUES ("Ford 150", "100 years of the same product");

INSERT INTO reviews (review_detail, product_id)
VALUES ("100 years of trust in just one truck.", 2);

INSERT INTO comments (comment_detail, review_id)
VALUES ("Its awesome.", 3);

INSERT INTO comments (comment_detail, review_id)
VALUES ("Nobody can beat Ford 150.", 3);

