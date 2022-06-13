package api.soldout.io.soldout.service.trade;

import api.soldout.io.soldout.dtos.entity.SaleDto;
import api.soldout.io.soldout.dtos.entity.SaleDto.SaleStatus;
import api.soldout.io.soldout.dtos.entity.TradeDto;
import api.soldout.io.soldout.dtos.entity.TradeDto.TradeStatus;
import api.soldout.io.soldout.exception.AlreadyMatchedException;
import api.soldout.io.soldout.repository.sale.SaleRepository;
import api.soldout.io.soldout.repository.trade.TradeRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * .
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {

  private final TradeRepository tradeRepository;

  private final SaleRepository saleRepository;

  @Override
  public void signTradeByOrder(int productId, int orderId, int size, int price) {

    List<SaleDto> saleDtoList = saleRepository.findByProductId(productId);

    int saleId = findMatchedSaleDto(saleDtoList, size, price);

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

    saleRepository.updateSaleStatus(saleId, SaleStatus.MATCHING_COMPLETE);

  }

  @Override
  public List<TradeDto> findByOrderId(int orderId) {

    return tradeRepository.findByOrderId(orderId);

  }

  @Override
  public List<TradeDto> findBySaleId(int saleId) {

    return tradeRepository.findBySaleId(saleId);

  }

  private int findMatchedSaleDto(List<SaleDto> saleDtoList, int size, int price) {

    // id가 기본적으로 인덱스로 사용되고 자동 증가로 입력되기 때문에 그 순서로 루프를 수행
    // 판매 상태가 상태 판매 입찰중이면서 가격과 사이즈가 같은 녀석을 조회
    Optional<SaleDto> findSaleDto =  saleDtoList.stream()
        .filter((saleDto) -> saleDto.getStatus().equals(SaleStatus.BID_PROGRESS)
                          && saleDto.getPrice() == price
                          && saleDto.getSize() == size)
        .findFirst();

    if (findSaleDto.isPresent()) {

      return findSaleDto.get().getId();

    } else {

      throw new AlreadyMatchedException("찾는 판매 입찰가가 없습니다.");

    }

  }

}
