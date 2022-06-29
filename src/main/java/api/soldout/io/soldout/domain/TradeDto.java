package api.soldout.io.soldout.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * .
 */

@Getter
@Builder
@AllArgsConstructor
public class TradeDto {

  /**
   * .
   */

  @Getter
  @AllArgsConstructor
  public enum TradeStatus {

    MATCHING_COMPLETE("거래 체결 완료"),
    DELIVER_FROM_SELLER_WAIT("판매자 발송 대기 중"),
    DELIVER_FROM_SELLER_COMPLETE("판매자 발송 완료"),
    INSPECTION_ARRIVAL("검수센터 도착"),
    INSPECTION_INSPECTING("검수센터 검수 중"),
    INSPECTION_PASS("검수센터 검수 통과"),
    DELIVER_TO_ORDER_DELIVERING("배송 중"),
    DELIVER_TO_ORDER_ARRIVAL("배송 도착"),
    TRADE_COMPLETE("거래 완료");

    private final String text;

  }

  private int id;
  private int productId;
  private int orderId;
  private int saleId;
  private int size;
  private int price;
  private TradeStatus status;
  private LocalDateTime date;

}
