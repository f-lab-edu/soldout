package api.soldout.io.soldout.controller.order;

import api.soldout.io.soldout.annotation.CheckSignIn;
import api.soldout.io.soldout.annotation.SignInUser;
import api.soldout.io.soldout.controller.order.request.OrderRequest;
import api.soldout.io.soldout.dtos.entity.UserDto;
import api.soldout.io.soldout.dtos.response.ResponseDto;
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
   * .
   */

  @PostMapping("/now/{productId}")
  @CheckSignIn
  public ResponseDto order(@Valid @RequestBody OrderRequest request,
                           @SignInUser UserDto user,
                           @PathVariable(value = "productId") int productId) {

    orderService.order(OrderRequest.toCommand(request, user.getId(), productId));

    return new ResponseDto(

        true, null, "구매 등록 완료", null

    );

  }

}
