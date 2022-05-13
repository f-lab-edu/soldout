package api.soldout.io.soldout.controller.product;

import api.soldout.io.soldout.controller.product.request.SaveRequest;
import api.soldout.io.soldout.dtos.ProductDto;
import api.soldout.io.soldout.dtos.response.ResponseDto;
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
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  /**
   * .
   */

  @PostMapping("/new")
  public ResponseDto save(@RequestBody SaveRequest request) {

    ProductDto product = productService.save(SaveRequest.toCommand(request));

    return new ResponseDto(true, product, "상품 저장", null);

  }

  /**
   * .
   */

  @GetMapping("/list")
  public ResponseDto productList() {

    Collection<ProductDto> productList = productService.findAllProduct();

    return new ResponseDto(true, productList, "상품 리스트", null);

  }
}
