package api.soldout.io.soldout.controller.sale;

import api.soldout.io.soldout.controller.sale.request.SaleBidRequest;
import api.soldout.io.soldout.dtos.entity.SaleDto;
import api.soldout.io.soldout.dtos.response.ResponseDto;
import api.soldout.io.soldout.service.sell.SaleService;
import java.util.List;
import javax.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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
   * .@param userId
   * .@param productId
   * .@return
   */

  @PostMapping("/bid/{userId}/{productId}")
  public ResponseDto saleBid(@RequestBody @Valid SaleBidRequest request,
                             @PathVariable(value = "userId") int userId,
                             @PathVariable(value = "productId")int productId) {

    saleService.saleBid(SaleBidRequest.toCommand(request, userId, productId));

    return new ResponseDto(true, null, "판매 입찰 등록 성공", null);

  }

}
