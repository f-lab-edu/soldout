package api.soldout.io.soldout.service.sell;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import api.soldout.io.soldout.dtos.entity.SaleDto;
import api.soldout.io.soldout.interceptor.SessionSignInHandlerInterceptor;
import api.soldout.io.soldout.repository.sell.SaleRepository;
import api.soldout.io.soldout.repository.sell.SaleRepositoryImpl;
import api.soldout.io.soldout.service.sell.command.SaleBidCommand;
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
  SaleRepository saleRepository;

  @BeforeEach
  void init() throws Exception {

  }

  @Test
  @DisplayName("판매 입찰 테스트")
  void saleBidTest() throws Exception {
    // given
    SaleBidCommand command =  new SaleBidCommand(
        1,
        1,
        220,
        100000,
        3,
        SaleType.SALE_BID
    );

    // when
    saleService.saleBid(command);

    // then
    verify(saleRepository).saveSale(any());
    verify(saleRepository, times(1)).saveSale(any());

  }
}