package api.soldout.io.soldout.service.trade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import api.soldout.io.soldout.domain.TradeDto;
import api.soldout.io.soldout.repository.trade.TradeRepository;
import api.soldout.io.soldout.repository.trade.TradeRepositoryImpl;
import java.util.ArrayList;
import java.util.List;
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

  int productId = 1;
  int orderId = 1;
  int saleId = 1;
  int size = 1;
  int price = 1;

  List<TradeDto> list;

  @BeforeEach
  void init() {

    tradeRepository = mock(TradeRepositoryImpl.class);

    tradeService = new TradeServiceImpl(tradeRepository);

    list = new ArrayList<>();

  }

  @Test
  @DisplayName("거래 체결 객체 저장 테스트")
  void saveTradeTest() {
    //given
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

  @Test
  @DisplayName("구매 Id로 조회 로직 테스트")
  void findByOrderIdTest() {
    //given
    TradeDto tradeDto = TradeDto.builder()
        .orderId(orderId)
        .build();

    list.add(tradeDto);

    //when
    when(tradeRepository.findByOrderId(orderId)).thenReturn(list);

    List<TradeDto> findList = tradeService.findByOrderId(orderId);

    // then
    verify(tradeRepository, times(1)).findByOrderId(orderId);

    assertThat(findList.size()).isEqualTo(list.size());

  }

  @Test
  @DisplayName("판매 Id로 조회 로직 테스트")
  void findBySaleIdTest() {
    //given
    TradeDto tradeDto = TradeDto.builder()
        .saleId(saleId)
        .build();

    list.add(tradeDto);

    //when
    when(tradeRepository.findByOrderId(saleId)).thenReturn(list);

    List<TradeDto> findList = tradeService.findByOrderId(saleId);

    // then
    verify(tradeRepository, times(1)).findByOrderId(saleId);

    assertThat(findList.size()).isEqualTo(list.size());

  }

}