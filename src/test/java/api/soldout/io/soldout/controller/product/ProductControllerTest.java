package api.soldout.io.soldout.controller.product;

import api.soldout.io.soldout.service.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  private ProductService productService;

  @BeforeEach
  void setUp() {

  }

  @Test
  void addProduct() {

  }

  @Test
  void getAllProducts() {

  }

}