package api.soldout.io.soldout.service.sell;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import api.soldout.io.soldout.dtos.entity.SaleDto;
import api.soldout.io.soldout.interceptor.SessionSignInHandlerInterceptor;
import api.soldout.io.soldout.repository.sell.SaleRepositoryImpl;
import api.soldout.io.soldout.util.enums.SaleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * .
 */

@ExtendWith(MockitoExtension.class)
class SaleServiceImplTest {

  @InjectMocks
  SaleServiceImpl saleService;

  @Mock
  SaleRepositoryImpl saleRepository;

  SessionSignInHandlerInterceptor interceptor;

  @BeforeEach
  void init() throws Exception {

    when(interceptor.preHandle(any(), any(), any())).thenReturn(true);

  }

  @Test
  @DisplayName("판매 입찰 테스트")
  void saleBidTest() throws Exception {

    // given
    SaleDto saleDto = SaleDto.builder()
        .userId(1)
        .productId(1)
        .size(250)
        .price(100000)
        .type(SaleType.SALE_BID)
        .day(3)
        .build();

    // when

    // then
    verify(saleRepository).saveSale(saleDto);

  }
}