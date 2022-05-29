package api.soldout.io.soldout.controller.order;

import api.soldout.io.soldout.controller.order.request.OrderRequest;
import api.soldout.io.soldout.dtos.entity.OrderDto;
import api.soldout.io.soldout.dtos.response.ResponseDto;
import api.soldout.io.soldout.service.order.OrderService;
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
   * 제품 Id외 사용자 Id에 대한 주문을 등록.
   */

  @PostMapping
  public ResponseDto order(@RequestBody OrderRequest request) {

    orderService.order(OrderRequest.toCommand(request));

    return new ResponseDto(true, null, null, null);

  }

  /**
   * .
   */

  @GetMapping("/{orderId}")
  public ResponseDto findByOrderId(@PathVariable("orderId") String orderId) {

    OrderDto orderDto = orderService.findByOrderId(orderId);

    return new ResponseDto(true, null, null, null);

  }

}
