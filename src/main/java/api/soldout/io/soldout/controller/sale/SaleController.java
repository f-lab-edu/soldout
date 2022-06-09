package api.soldout.io.soldout.controller.sale;

import api.soldout.io.soldout.annotation.CheckSignIn;
import api.soldout.io.soldout.annotation.SignInUser;
import api.soldout.io.soldout.controller.sale.request.SaleBidRequest;
import api.soldout.io.soldout.dtos.entity.UserDto;
import api.soldout.io.soldout.dtos.response.ResponseDto;
import api.soldout.io.soldout.service.sale.SaleService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * .
 */

@Slf4j
@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SaleController {

  private final SaleService saleService;

  /**
   * 판매 입찰 등록.
   * .@param request
   * .@paran UserDto
   * .@param productId
   * .@return
   */

  @PostMapping("/bid/{productId}")
  @CheckSignIn
  public ResponseDto saleBid(@RequestBody @Valid SaleBidRequest request,
                             @SignInUser UserDto user,
                             @PathVariable(value = "productId") int productId) {

    saleService.saleBid(SaleBidRequest.toCommand(request, user.getId(), productId));

    return new ResponseDto(true, null, "판매 입찰 등록 성공", null);

  }

}
