package api.soldout.io.soldout.mapper;

import api.soldout.io.soldout.config.MyBatisConfig;
import api.soldout.io.soldout.dtos.entity.TradeDto;
import api.soldout.io.soldout.dtos.entity.TradeDto.TradeStatus;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@Slf4j
@MybatisTest
@ImportAutoConfiguration(MyBatisConfig.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class TradeMapperTest {

  @Autowired
  private TradeMapper tradeMapper;

  @BeforeEach
  void init() {

  }

  @Disabled
  @Test
  @DisplayName("즉시 구매 요청에 따른 거래 채결")
  void insertTest() {

    TradeDto tradeDto = TradeDto.builder()
        .productId(53)
        .orderId(17)
        .saleId(2)
        .size(240)
        .price(120000)
        .status(TradeStatus.TRADE_SIGNING)
        .day(LocalDate.now())
        .build();

    tradeMapper.insertTrade(tradeDto);

  }

}