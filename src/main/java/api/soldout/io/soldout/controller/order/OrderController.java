package api.soldout.io.soldout.controller.order;

import api.soldout.io.soldout.annotation.CheckSignIn;
import api.soldout.io.soldout.annotation.SignInUserId;
import api.soldout.io.soldout.controller.request.OrderNowRequest;
import api.soldout.io.soldout.controller.response.ResponseDto;
import api.soldout.io.soldout.service.order.OrderService;
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
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  /**
   * 즉시 구매 API.
   */

  @PostMapping("/now/{productId}")
  @CheckSignIn
  public ResponseDto orderNow(@Valid @RequestBody OrderNowRequest request,
                              @SignInUserId int userId,
                              @PathVariable(value = "productId") int productId) {

    orderService.orderNow(OrderNowRequest.toCommand(request, userId, productId));

    return new ResponseDto(

        true, null, "즉시 구매 등록 완료", null

    );

  }

}
