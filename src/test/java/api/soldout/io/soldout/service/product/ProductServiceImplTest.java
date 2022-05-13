package api.soldout.io.soldout.service.product;

import api.soldout.io.soldout.controller.product.request.SaveRequest;
import api.soldout.io.soldout.service.product.command.SaveCommand;
import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    SaveRequest request = new SaveRequest(
        "Nike Dunk Low Retro Black",
        "nike",
        "black/white",
        "2021/01/14",
        "DD1391-100",
        imagesLink
    );

    SaveCommand command = SaveRequest.toCommand(request);

    productService.save(command);

  }

  @Test
  void findAllProduct() {

    Assertions.assertThat(productService.findAllProduct().size()).isEqualTo(1);

  }
}