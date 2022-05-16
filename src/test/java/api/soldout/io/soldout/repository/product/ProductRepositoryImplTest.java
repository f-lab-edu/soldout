package api.soldout.io.soldout.repository.product;

import api.soldout.io.soldout.dtos.ProductDto;
import api.soldout.io.soldout.service.product.command.AddProductCommand;
import java.util.ArrayList;
import java.util.Collection;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ProductRepositoryImplTest.
 * 테스트 클래스에서 의존성 주입을 위해선 @SpringBootTest를 해야만 한다.
 */

@SpringBootTest
class ProductRepositoryImplTest {

  @Autowired
  private ProductRepository productRepository;

  @BeforeEach
  void before() {
    ArrayList<String> imagesLink = new ArrayList<>();

    imagesLink.add(0, "testLink1");
    imagesLink.add(1, "testLink2");
    imagesLink.add(2, "testLink3");

    ProductDto product1 = ProductDto
        .builder()
        .name("Nike Dunk Low Retro Black")
        .brand("nike")
        .color("black/white")
        .imagesLink(imagesLink)
        .modelNumber("DD1391-100")
        .releaseDay(AddProductCommand.toLocalDate("2021/01/14"))
        .build();

    productRepository.save(product1);

  }

  @Test
  void testFindAllProduct() {

    Collection<ProductDto> products = productRepository.findAll();

    Assertions.assertThat(products.size()).isSameAs(1);

  }

  @AfterEach
  void after() {

    productRepository.clear();

  }

}