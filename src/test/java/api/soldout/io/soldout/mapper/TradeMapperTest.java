package api.soldout.io.soldout.mapper;

import api.soldout.io.soldout.config.MyBatisConfig;
import api.soldout.io.soldout.dtos.entity.TradeDto;
import api.soldout.io.soldout.dtos.entity.TradeDto.TradeStatus;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ImportAutoConfiguration(MyBatisConfig.class)
class TradeMapperTest {

  @Autowired
  private TradeMapper tradeMapper;

  @Test
  void insertTest() throws Exception {

    TradeDto tradeDto = TradeDto.builder()
        .productId(51)
        .orderId(16)
        .saleId(1)
        .size(250)
        .price(100000)
        .status(TradeStatus.TRADE_SIGNING)
        .day(LocalDate.now())
        .build();

    tradeMapper.insertTrade(tradeDto);

  }

}