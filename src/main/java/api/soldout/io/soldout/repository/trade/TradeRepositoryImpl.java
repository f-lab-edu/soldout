package api.soldout.io.soldout.repository.trade;

import api.soldout.io.soldout.dtos.entity.TradeDto;
import api.soldout.io.soldout.mapper.TradeMapper;
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

}
