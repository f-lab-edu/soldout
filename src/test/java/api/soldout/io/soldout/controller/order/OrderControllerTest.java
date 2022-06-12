package api.soldout.io.soldout.controller.order;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import api.soldout.io.soldout.controller.order.request.OrderNowRequest;
import api.soldout.io.soldout.dtos.response.ResponseDto;
import api.soldout.io.soldout.interceptor.SessionSignInHandlerInterceptor;
import api.soldout.io.soldout.resolver.SignInUserArgumentResolver;
import api.soldout.io.soldout.service.order.OrderServiceImpl;
import api.soldout.io.soldout.util.enums.OrderType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
  OrderServiceImpl orderService;

  @MockBean
  SessionSignInHandlerInterceptor interceptor;

  @MockBean
  SignInUserArgumentResolver signInUserArgumentResolver;

  ObjectMapper objectMapper;

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
    int userId = 1;

    OrderNowRequest request =
        new OrderNowRequest(250, 100000, 3, OrderType.ORDER_NOW);

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

    verify(orderService).orderNow(any());
    verify(orderService, times(1)).orderNow(any());

  }



}