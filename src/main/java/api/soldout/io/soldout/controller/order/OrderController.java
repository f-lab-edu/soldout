package api.soldout.io.soldout.controller.order;

import api.soldout.io.soldout.annotation.CheckSignIn;
import api.soldout.io.soldout.controller.order.request.OrderRequest;
import api.soldout.io.soldout.dtos.entity.OrderDto;
import api.soldout.io.soldout.dtos.response.ResponseDto;
import api.soldout.io.soldout.dtos.response.data.GetOrderByIdData;
import api.soldout.io.soldout.service.order.OrderService;
import java.util.List;
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
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  /**
   * 사용자가 입력한 구매 입찰 정보를 통해 구매 입찰가 등록을 진행한다.
     * 즉시 구매(ORDER_NOW)일 경우, 해당 제품의 사이즈로 등록된 판매 입찰가 중 최고액으로 등록된 사용자와 연결한다.
     * 구매 입찰(ORDER_BID)일 경우, 입력된 입찰 기간만큼 데이터를 유지하고 같은 가격의 판매 희망자오 연결한다.
   *
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
