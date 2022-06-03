package api.soldout.io.soldout.controller.product;

import api.soldout.io.soldout.controller.product.request.AddProductRequest;
import api.soldout.io.soldout.dtos.entity.ProductDto;
import api.soldout.io.soldout.dtos.response.ResponseDto;
import api.soldout.io.soldout.dtos.response.data.GetAllProductsData;
import api.soldout.io.soldout.service.product.ProductService;
import java.util.List;
import javax.validation.Valid;
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
   * 상품 정보 저장.
   */

  @PostMapping
  public ResponseDto addProduct(@RequestBody @Valid AddProductRequest request) {

    productService.addProduct(AddProductRequest.toCommand(request));

    return new ResponseDto(

        true, null, "상품 저장", null

    );

  }

  /**
   * 전체 상품 목록 조회.
   */

  @GetMapping
  public ResponseDto getAllProducts() {

    List<ProductDto> productList = productService.findAll();

    return new ResponseDto(

        true, GetAllProductsData.from(productList), "상품 리스트", null

    );

  }

}
