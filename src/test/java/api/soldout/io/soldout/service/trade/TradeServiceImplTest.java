package api.soldout.io.soldout.service.trade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import api.soldout.io.soldout.dtos.entity.SaleDto;
import api.soldout.io.soldout.dtos.entity.SaleDto.SaleStatus;
import api.soldout.io.soldout.repository.sale.SaleRepository;
import api.soldout.io.soldout.repository.sale.SaleRepositoryImpl;
import api.soldout.io.soldout.repository.trade.TradeRepository;
import api.soldout.io.soldout.repository.trade.TradeRepositoryImpl;
import api.soldout.io.soldout.util.enums.SaleType;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TradeServiceImplTest {

  private TradeService tradeService;

  private SaleRepository saleRepository;

  private TradeRepository tradeRepository;

  @BeforeEach
  void init() {

    saleRepository = mock(SaleRepositoryImpl.class);

    tradeRepository = mock(TradeRepositoryImpl.class);

    tradeService = new TradeServiceImpl(tradeRepository, saleRepository);

  }

  @Test
  @DisplayName("즉시 구매 요청에 따른 거래 채결 테스트")
  void signTradeByOrderTest() {
    // given
    int productId = 1;
    int orderId = 1;
    int size = 250;
    int price = 100000;

    SaleDto saleDto = SaleDto.builder()
        .id(1)
        .productId(productId)
        .userId(1)
        .size(size)
        .price(price)
        .date(3)
        .type(SaleType.SALE_BID)
        .status(SaleStatus.BID_PROGRESS)
        .build();

    List<SaleDto> saleDtoList = new ArrayList<>();
    saleDtoList.add(saleDto);


    // when
    when(saleRepository.findByProductId(productId))
        .thenReturn(saleDtoList);

    tradeService.signTradeByOrder(productId, orderId, size, price);

    // then
    assertThat(saleRepository.findByProductId(productId))
        .isEqualTo(saleDtoList);

    verify(tradeRepository)
        .saveTrade(any());

    verify(tradeRepository, times(1))
        .saveTrade(any());

    verify(saleRepository)
        .updateSaleStatus(saleDto.getId(), SaleStatus.MATCHING_COMPLETE);

    verify(saleRepository, times(1))
        .updateSaleStatus(saleDto.getId(), SaleStatus.MATCHING_COMPLETE);

  }

}