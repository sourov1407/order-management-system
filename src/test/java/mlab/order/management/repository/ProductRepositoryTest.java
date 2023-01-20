package mlab.order.management.repository;

import mlab.order.management.model.entity.ProductEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@TestPropertySource("classpath:application.yml")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void createProductTest() {
        ProductEntity productEntity = ProductEntity.builder()
                .name("test name")
                .category("test category")
                .count(100)
                .skuId("test sku")
                .issueDate("01-10-2023")
                .build();

        productRepository.save(productEntity);

        Assertions.assertThat(productEntity.getId()).isGreaterThan(0);
    }

    @Test
    public void findAllProductTest() {
        ProductEntity productEntity = ProductEntity.builder()
                .name("test name")
                .category("test category")
                .count(100)
                .skuId("test sku")
                .issueDate("01-10-2023")
                .build();

        productRepository.save(productEntity);
        ProductEntity result = productRepository.findAll().get(0);

        Assertions.assertThat(result.getId()).isGreaterThan(0);
        Assertions.assertThat(result.getName()).isEqualTo(productEntity.getName());
        Assertions.assertThat(result.getCategory()).isEqualTo(productEntity.getCategory());
        Assertions.assertThat(result.getCount()).isEqualTo(productEntity.getCount());
        Assertions.assertThat(result.getSkuId()).isEqualTo(productEntity.getSkuId());
        Assertions.assertThat(result.getIssueDate()).isEqualTo(productEntity.getIssueDate());

    }

}