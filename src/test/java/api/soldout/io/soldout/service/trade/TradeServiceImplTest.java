package api.soldout.io.soldout.service.trade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import api.soldout.io.soldout.dtos.entity.TradeDto;
import api.soldout.io.soldout.repository.trade.TradeRepository;
import api.soldout.io.soldout.repository.trade.TradeRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TradeServiceImplTest {

  TradeService tradeService;

  TradeRepository tradeRepository;



  @BeforeEach
  void init() {

    tradeRepository = mock(TradeRepositoryImpl.class);

    tradeService = new TradeServiceImpl(tradeRepository);

  }

  @Test
  @DisplayName("거래 체결 객체 저장 테스트")
  void saveTradeTest() {
    //given
    int productId = 1;
    int orderId = 1;
    int saleId = 1;
    int size = 1;
    int price = 1;

    ArgumentCaptor<TradeDto> captor = ArgumentCaptor.forClass(TradeDto.class);

    //when
    tradeService.saveTrade(productId, orderId, saleId, size, price);

    //then
    verify(tradeRepository, times(1))
        .saveTrade(captor.capture());

    TradeDto trade = captor.getValue();

    assertThat(trade.getProductId()).isEqualTo(productId);
    assertThat(trade.getOrderId()).isEqualTo(orderId);
    assertThat(trade.getSaleId()).isEqualTo(saleId);
    assertThat(trade.getSize()).isEqualTo(size);
    assertThat(trade.getPrice()).isEqualTo(price);

  }

}