package api.soldout.io.soldout.controller.sale;

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
import api.soldout.io.soldout.service.sell.SaleServiceImpl;
import api.soldout.io.soldout.util.enums.SaleType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
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

  ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

  @BeforeEach
  void init() throws Exception {

    when(interceptor.preHandle(any(), any(), any())).thenReturn(true);

  }

  @Test
  @DisplayName("판매 입찰 등록 테스트")
  void saleBidTest() throws Exception {

    // given
    SaleBidRequest request =
        new SaleBidRequest(250, 100000, 3, SaleType.SALE_BID);

    ResponseDto response =
        new ResponseDto(true, null, "판매 입찰 등록 성공", null);


    // when
    ResultActions result = mockMvc.perform(post("/sale/bid/1/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)));

    //then
    result.andExpect(status().isOk())
          .andExpect(content().json(objectMapper.writeValueAsString(response)))
          .andDo(print());

    verify(saleService).saleBid(any());
    verify(saleService, times(1)).saleBid(any());

  }

}