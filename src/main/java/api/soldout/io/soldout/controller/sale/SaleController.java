package api.soldout.io.soldout.controller.sale;

import api.soldout.io.soldout.annotation.CheckSignIn;
import api.soldout.io.soldout.annotation.SignInUserId;
import api.soldout.io.soldout.controller.request.SaleBidRequest;
import api.soldout.io.soldout.controller.response.ResponseDto;
import api.soldout.io.soldout.service.sale.SaleService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SaleController {

  private final SaleService saleService;

  /**
   * 판매 입찰 등록.
   */

  @PostMapping("/bid/{productId}")
  @CheckSignIn
  public ResponseDto saleBid(@RequestBody @Valid SaleBidRequest request,
                             @SignInUserId int userId,
                             @PathVariable(value = "productId") int productId) {

    saleService.saleBid(SaleBidRequest.toCommand(request, userId, productId));

    return new ResponseDto(true, null, "판매 입찰 등록 성공", null);

  }

}
