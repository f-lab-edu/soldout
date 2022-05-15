package api.soldout.io.soldout.service.product;

import api.soldout.io.soldout.controller.product.request.AddProductRequest;
import api.soldout.io.soldout.dtos.ProductDto;
import api.soldout.io.soldout.service.product.command.AddProductCommand;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
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

    AddProductCommand command = AddProductRequest.toCommand(request);

    ProductDto product = productService.addProduct(command);

  }

  @Test
  void findAllProduct() {

    Assertions.assertThat(productService.findAll().size()).isEqualTo(1);

  }
}