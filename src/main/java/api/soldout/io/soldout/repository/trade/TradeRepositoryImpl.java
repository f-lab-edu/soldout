package api.soldout.io.soldout.repository.trade;

import api.soldout.io.soldout.domain.TradeDto;
import api.soldout.io.soldout.mapper.TradeMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * .
 */

@Repository
@RequiredArgsConstructor
public class TradeRepositoryImpl implements TradeRepository {

  private final TradeMapper tradeMapper;

  @Override
  public void saveTrade(TradeDto tradeDto) {

    tradeMapper.insertTrade(tradeDto);

  }

  @Override
  public List<TradeDto> findByOrderId(int orderId) {

    return tradeMapper.findByOrderId(orderId);

  }

  @Override
  public List<TradeDto> findBySaleId(int saleId) {

    return tradeMapper.findBySaleId(saleId);

  }

}
