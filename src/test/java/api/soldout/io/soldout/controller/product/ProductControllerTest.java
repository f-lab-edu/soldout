package api.soldout.io.soldout.controller.product;

import api.soldout.io.soldout.controller.product.request.AddProductRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
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

    imagesLink.add(0, "testLink1");
    imagesLink.add(1, "testLink2");
    imagesLink.add(2, "testLink3");

    LocalDate localDate = LocalDate.parse("2021-01-14");

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
  void testProductList() {

    Assertions.assertThat(productController.getAllProducts().getError()).isEqualTo(null);

  }

  @AfterEach
  void after() {

    productController.deleteAll();

  }

}