package api.soldout.io.soldout.controller.product;

import static org.assertj.core.api.Assertions.*;

import api.soldout.io.soldout.controller.product.request.AddProductRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductControllerTest {

  @Autowired
  private ProductController productController;

  @BeforeEach
  void before() {
    ArrayList<String> imagesLink = new ArrayList<>();

    imagesLink.add(0, "https://kream-phinf.pstatic.net/MjAyMTA3MjhfMjQx/M…_8d86fe659c3542b2aaafa40a7a0048c1.png?type=l_webp");
    imagesLink.add(1, "https://kream-phinf.pstatic.net/MjAyMTA3MjhfMjQx/M…_8d86fe659c3542b2aaafa40a7a0048c1.png?type=l_webp");
    imagesLink.add(2, "https://kream-phinf.pstatic.net/MjAyMTA3MjhfMjQx/M…_8d86fe659c3542b2aaafa40a7a0048c1.png?type=l_webp");

    LocalDate localDate = LocalDate.parse("2021/01/14");

    AddProductRequest request = new AddProductRequest(
        "Nike Dunk Low Retro Black",
        "nike",
        "black/white",
        localDate,
        "DD1391-100",
        imagesLink
    );

    productController.addProduct(request);

  }

  @Test
  @DisplayName("상품 리스트 탐색")
  void productList() {

    assertThat(productController.getAllProducts().getError()).isEqualTo(null);

  }

}