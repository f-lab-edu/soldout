package api.soldout.io.soldout.controller.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import api.soldout.io.soldout.controller.request.OrderNowRequest;
import api.soldout.io.soldout.controller.response.ResponseDto;
import api.soldout.io.soldout.domain.OrderDto.OrderType;
import api.soldout.io.soldout.interceptor.SessionSignInHandlerInterceptor;
import api.soldout.io.soldout.resolver.SignInUserArgumentResolver;
import api.soldout.io.soldout.service.order.OrderService;
import api.soldout.io.soldout.service.order.command.OrderCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  OrderService orderService;

  @MockBean
  SessionSignInHandlerInterceptor interceptor;

  @MockBean
  SignInUserArgumentResolver signInUserArgumentResolver;

  ObjectMapper objectMapper;

  int userId = 1;
  int size = 250;
  int price = 100000;
  int period = 3;
  OrderType type = OrderType.ORDER_NOW;

  @BeforeEach
  void init() throws Exception {

    objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    when(interceptor.preHandle(any(), any(), any()))
        .thenReturn(true);

  }

  @Test
  @DisplayName("즉시 구매 등록 테스트")
  void orderNowTest() throws Exception {
    // given
    ArgumentCaptor<OrderCommand> orderCommandCap = ArgumentCaptor.forClass(OrderCommand.class);

    OrderNowRequest request =
        new OrderNowRequest(size, price, period, type);

    ResponseDto response =
        new ResponseDto(true, null, "즉시 구매 등록 완료", null);

    // when
    when(signInUserArgumentResolver.resolveArgument(any(), any(), any(), any()))
        .thenReturn(userId);

    ResultActions result = mockMvc.perform(post("/order/now/1")
        .param("userId", String.valueOf(userId))
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)));

    //then
    result.andExpect(status().isOk())
          .andExpect(content().json(objectMapper.writeValueAsString(response)))
          .andDo(print());

    verify(orderService, times(1)).orderNow(orderCommandCap.capture());

    OrderCommand command = orderCommandCap.getValue();

    assertThat(command.getPeriod()).isEqualTo(request.getPeriod());
    assertThat(command.getPrice()).isEqualTo(request.getPrice());
    assertThat(command.getSize()).isEqualTo(request.getSize());

  }



}