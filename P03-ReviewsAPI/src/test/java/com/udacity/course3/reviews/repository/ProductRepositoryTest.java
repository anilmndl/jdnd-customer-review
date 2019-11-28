package com.udacity.course3.reviews.repository;


import com.udacity.course3.reviews.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ProductRepositoryTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void injected_components_are_not_null(){
        Assert.assertNotNull(dataSource);
        Assert.assertNotNull(jdbcTemplate);
        Assert.assertNotNull(entityManager);
        Assert.assertNotNull(testEntityManager);
        Assert.assertNotNull(productRepository);

    }

    @Test
    public void product_repository_returns_product_by_id(){
        // create product
        Product product = new Product();
        product.setProductTitle("Test Product");
        product.setProductDetail("Test product detail");

        // save it
        entityManager.persist(product);

        Optional<Product> productOptional = productRepository.findById(product.getId());
        Product actual = productOptional.get();

        // check to make sure things are as expected
        Assert.assertNotNull(actual);
        Assert.assertEquals(product.getProductTitle(), actual.getProductTitle());
    }

}
