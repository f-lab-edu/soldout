package api.soldout.io.soldout.mapper;

import api.soldout.io.soldout.dtos.entity.TradeDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * .
 */

@Mapper
public interface TradeMapper {

  void insertTrade(TradeDto tradeDto);

  List<TradeDto> findByOrderId(int orderId);

  List<TradeDto> findBySaleId(int saleId);

}
