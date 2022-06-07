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

  @Test
  @DisplayName("Mapper 인터페이스 객체가 NPE를 발생시키는 이유를 찾기 위한 테스트")
  void autoWiredMapperTest() {
    log.info("tradeMapper.toString() = {}", tradeMapper.toString());
    // log -> "tradeMapper.toString() = org.apache.ibatis.binding.MapperProxy@61ab6521"
    // 프록시 객체가 생성되어 있는데 메소드 호출시 NPE가 발생하는 이유는?
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