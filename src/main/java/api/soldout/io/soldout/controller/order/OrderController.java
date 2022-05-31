package api.soldout.io.soldout.controller.order;

import api.soldout.io.soldout.annotation.CheckSignIn;
import api.soldout.io.soldout.controller.order.request.OrderRequest;
import api.soldout.io.soldout.dtos.response.ResponseDto;
import api.soldout.io.soldout.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * .
 */

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  /**
   * 사용자가 입력한 구매 입찰 정보를 통해 구매 입찰가 등록을 진행한다.
   * 즉시 구매(ORDER_NOW)일 경우, 해당 제품의 사이즈로 등록된 판매 입찰가 중 최고액으로 등록된 사용자와 연결한다.
     * 제품, 사이즈가 동일한 판매 입찰가 중 최고 판매 입찰가와 연결(Ex. TradeService.trade())
   * 구매 입찰(ORDER_BID)일 경우, 입력된 입찰 기간만큼 데이터를 유지하고 같은 가격의 판매 희망자와 연결한다.
     * 주기적으로 입찰가를 확인하는 방법이 필요하다.
     * 경우 1 : 처음 구매 입찰을 등록하는 경우 -> 판매가와 비교
     * 경우 2 : 구매 입찰가를 수정하는 경우 -> 판매가와 비교
   */

  @PostMapping
  @CheckSignIn
  public ResponseDto order(@RequestBody OrderRequest request) {

    orderService.order(OrderRequest.toCommand(request));

    return new ResponseDto(

        true, null, "구매 등록 완료", null

    );

  }

}
