package api.soldout.io.soldout.repository.product;

import static org.assertj.core.api.Assertions.*;

import api.soldout.io.soldout.dtos.ProductDto;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    imagesLink.add(0, "https://kream-phinf.pstatic.net/MjAyMTA3MjhfMjQx/M…_8d86fe659c3542b2aaafa40a7a0048c1.png?type=l_webp");
    imagesLink.add(1, "https://kream-phinf.pstatic.net/MjAyMTA3MjhfMjQx/M…_8d86fe659c3542b2aaafa40a7a0048c1.png?type=l_webp");
    imagesLink.add(2, "https://kream-phinf.pstatic.net/MjAyMTA3MjhfMjQx/M…_8d86fe659c3542b2aaafa40a7a0048c1.png?type=l_webp");

    ProductDto product1 = ProductDto
        .builder()
        .name("Nike Dunk Low Retro Black")
        .brand("nike")
        .color("black/white")
        .imagesLink(imagesLink)
        .modelNumber("DD1391-100")
        .releaseDay("2021/01/14")
        .build();

    productRepository.save(product1);

  }

  @AfterEach
  void after() {
    productRepository.clear();
  }

  @Test
  void findAllProduct() {

    assertThat(productRepository.findAllProduct().size()).isEqualTo(1);

  }
}