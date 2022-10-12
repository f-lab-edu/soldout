package api.soldout.io.soldout.service.trade;

import api.soldout.io.soldout.domain.TradeDto;
import api.soldout.io.soldout.domain.TradeDto.TradeStatus;
import api.soldout.io.soldout.repository.trade.TradeRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {

  private final TradeRepository tradeRepository;

  @Override
  @Transactional
  public void saveTrade(int productId, int orderId, int saleId, int size, int price) {

    TradeDto tradeDto = TradeDto.builder()
        .productId(productId)
        .orderId(orderId)
        .saleId(saleId)
        .size(size)
        .price(price)
        .status(TradeStatus.MATCHING_COMPLETE)
        .date(LocalDateTime.now())
        .build();

    tradeRepository.saveTrade(tradeDto);

  }

  @Override
  @Transactional(readOnly = true)
  public List<TradeDto> findByOrderId(int orderId) {

    return tradeRepository.findByOrderId(orderId);

  }

  @Override
  @Transactional(readOnly = true)
  public List<TradeDto> findBySaleId(int saleId) {

    return tradeRepository.findBySaleId(saleId);

  }

}
