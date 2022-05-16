package api.soldout.io.soldout.service.product;

import api.soldout.io.soldout.controller.product.request.AddProductRequest;
import api.soldout.io.soldout.dtos.ProductDto;
import api.soldout.io.soldout.service.product.command.AddProductCommand;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ProductServiceImplTest {

  @Autowired
  private ProductService productService;

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

    AddProductCommand command = AddProductRequest.toCommand(request);

    productService.addProduct(command);

  }

  @Test
  void testFindAllProduct() {

    Collection<ProductDto> products = productService.findAll();

    Assertions.assertThat(products.size()).isSameAs(1);

  }

  @AfterEach
  void after() {

    productService.clear();

  }

}