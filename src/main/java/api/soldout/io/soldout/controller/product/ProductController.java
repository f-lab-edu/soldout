package api.soldout.io.soldout.controller.product;

import api.soldout.io.soldout.controller.product.request.AddProductRequest;
import api.soldout.io.soldout.dtos.ProductDto;
import api.soldout.io.soldout.dtos.response.ResponseDto;
import api.soldout.io.soldout.dtos.response.data.AddProductData;
import api.soldout.io.soldout.dtos.response.data.GetAllProductsData;
import api.soldout.io.soldout.service.product.ProductService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProductController.
 */

@Slf4j
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  /**
   * .
   */

  @PostMapping
  public ResponseDto addProduct(@RequestBody AddProductRequest request) {

    ProductDto product = productService.addProduct(AddProductRequest.toCommand(request));

    return new ResponseDto(
        true,
        AddProductData.from(product),
        "상품 저장",
        null
    );

  }

  /**
   * .
   */

  @GetMapping
  public ResponseDto getAllProducts() {

    Collection<ProductDto> productList = productService.findAll();

    return new ResponseDto(
        true,
        GetAllProductsData.from(productList),
        "상품 리스트",
        null
    );

  }
}
