package api.soldout.io.soldout.service.trade;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import api.soldout.io.soldout.repository.trade.TradeRepository;
import api.soldout.io.soldout.service.sale.SaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TradeServiceImplTest {

  private TradeService tradeService;

  @Mock
  private SaleService saleService;

  @Mock
  private TradeRepository tradeRepository;

  private TradeService tradeService(SaleService saleService, TradeRepository tradeRepository) {

    return new TradeServiceImpl(saleService, tradeRepository);

  }


  @BeforeEach
  void init() {

    tradeService = tradeService(saleService, tradeRepository);

  }

  @Test
  @DisplayName("즉시 구매 요청에 따른 거래 채결 텟스트")
  void signTradeByOrderTest() {
    // given
    int productId = 1;
    int orderId = 1;
    int size = 250;
    int price = 100000;

    // when
    tradeService.signTradeByOrder(productId, orderId, size, price);

    //then
    verify(saleService).findByProductId(productId);
    verify(saleService, times(1)).findByProductId(productId);

    verify(tradeRepository).saveTrade(any());
    verify(tradeRepository, times(1)).saveTrade(any());
  }
}