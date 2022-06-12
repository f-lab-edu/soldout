package api.soldout.io.soldout.service.trade;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import api.soldout.io.soldout.repository.sale.SaleRepository;
import api.soldout.io.soldout.repository.trade.TradeRepository;
import api.soldout.io.soldout.service.sale.SaleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TradeServiceImplTest {

  private TradeService tradeService;

  @Mock
  private SaleRepository saleRepository;

  @Mock
  private TradeRepository tradeRepository;

  private TradeService tradeService(
      TradeRepository tradeRepository, SaleRepository saleRepository) {

    return new TradeServiceImpl(tradeRepository, saleRepository);

  }


  @BeforeEach
  void init() {

    tradeService = tradeService(tradeRepository, saleRepository);

  }

  @Test
  @DisplayName("즉시 구매 요청에 따른 거래 채결 텟스트")
  @Disabled
  void signTradeByOrderTest() {
    // given
    int productId = 1;
    int orderId = 1;
    int size = 250;
    int price = 100000;
    // when
    // tradeService.signTradeByOrder(productId, orderId, size, price);

    //then
    // verify(saleRepository).findByProductId(productId);
    // verify(saleRepository, times(1)).findByProductId(productId);

    // verify(tradeRepository).saveTrade(any());
    // verify(tradeRepository, times(1)).saveTrade(any());
  }
}