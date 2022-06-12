package api.soldout.io.soldout.service.trade;

import static api.soldout.io.soldout.dtos.entity.TradeDto.TradeStatus.TRADE_SIGNING;

import api.soldout.io.soldout.dtos.entity.SaleDto;
import api.soldout.io.soldout.dtos.entity.TradeDto;
import api.soldout.io.soldout.repository.trade.TradeRepository;
import api.soldout.io.soldout.service.sale.SaleService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * .
 */

@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {

  private final SaleService saleService;

  private final TradeRepository tradeRepository;

  @Override
  public void signTradeByOrder(int productId, int orderId, int size, int price) {

    List<SaleDto> saleDtoList = saleService.findByProductId(productId);

    int saleId = findMatchedSaleDto(saleDtoList, size, price);

    TradeDto tradeDto = TradeDto.builder()
        .productId(productId)
        .orderId(orderId)
        .saleId(saleId)
        .size(size)
        .price(price)
        .status(TRADE_SIGNING)
        .day(LocalDateTime.now())
        .build();

    tradeRepository.saveTrade(tradeDto);

  }

  private int findMatchedSaleDto(List<SaleDto> saleDtoList, int size, int price) {

    for (SaleDto tempSaleDto : saleDtoList) {

      if (tempSaleDto.getSize() == size && tempSaleDto.getPrice() == price) {

        return tempSaleDto.getId();

      }

    }

    return 0;

  }

}
