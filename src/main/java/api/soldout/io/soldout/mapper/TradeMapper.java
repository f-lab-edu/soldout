package api.soldout.io.soldout.mapper;

import api.soldout.io.soldout.dtos.entity.TradeDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * .
 */

@Mapper
public interface TradeMapper {

  void insertTrade(TradeDto tradeDto);

}
