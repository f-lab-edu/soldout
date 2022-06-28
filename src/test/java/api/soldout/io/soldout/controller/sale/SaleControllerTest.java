package api.soldout.io.soldout.controller.sale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import api.soldout.io.soldout.controller.sale.request.SaleBidRequest;
import api.soldout.io.soldout.dtos.response.ResponseDto;
import api.soldout.io.soldout.interceptor.SessionSignInHandlerInterceptor;
import api.soldout.io.soldout.resolver.SignInUserArgumentResolver;
import api.soldout.io.soldout.service.sale.SaleServiceImpl;
import api.soldout.io.soldout.service.sale.command.SaleBidCommand;
import api.soldout.io.soldout.util.enums.SaleType;
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

@WebMvcTest(SaleController.class)
class SaleControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  SaleServiceImpl saleService;

  @MockBean
  SessionSignInHandlerInterceptor interceptor;

  @MockBean
  SignInUserArgumentResolver signInUserArgumentResolver;

  ObjectMapper objectMapper;

  int userId = 1;
  int productId = 1;
  int size = 250;
  int price = 100000;
  int period = 3;
  SaleType type = SaleType.SALE_BID;

  @BeforeEach
  void init() throws Exception {

    objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    when(interceptor.preHandle(any(), any(), any()))
        .thenReturn(true);

  }

  @Test
  @DisplayName("판매 입찰 등록 테스트")
  void saleBidTest() throws Exception {
    // given
    ArgumentCaptor<SaleBidCommand> captor = ArgumentCaptor.forClass(SaleBidCommand.class);

    SaleBidRequest request =
        new SaleBidRequest(size, price, period, type);

    ResponseDto response =
        new ResponseDto(true, null, "판매 입찰 등록 성공", null);

    // when
    when(signInUserArgumentResolver.resolveArgument(any(), any(), any(), any()))
        .thenReturn(userId);

    ResultActions result = mockMvc.perform(post("/sale/bid/" + productId)
        .param("userId", String.valueOf(userId))
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)));

    //then
    result.andExpect(status().isOk())
          .andExpect(content().json(objectMapper.writeValueAsString(response)))
          .andDo(print());

    verify(saleService, times(1)).saleBid(captor.capture());

    SaleBidCommand command = captor.getValue();

    assertThat(command.getUserId()).isEqualTo(userId);
    assertThat(command.getProductId()).isEqualTo(productId);
    assertThat(command.getPeriod()).isEqualTo(request.getPeriod());
    assertThat(command.getPrice()).isEqualTo(request.getPrice());
    assertThat(command.getSize()).isEqualTo(request.getSize());

  }

}