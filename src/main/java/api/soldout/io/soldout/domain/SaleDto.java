package api.soldout.io.soldout.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaleDto {

  @Getter
  @AllArgsConstructor
  public enum SaleType {

    SALE_NOW("즉시 판매"),
    SALE_BID("판매 입찰");

    private final String text;
  }

  @Getter
  @AllArgsConstructor
  public enum SaleStatus {

    BID_PROGRESS("입찰 진행 중"),
    MATCHING_COMPLETE("거래 체결 완료");

    private final String text;

  }

  private int id;
  private int userId;
  private int productId;
  private int size;
  private int price;
  private LocalDateTime date;
  private SaleType type;
  private SaleStatus status;

  @Builder
  public SaleDto(int id, int userId, int productId, int size, int price, int date,
                 SaleType type, SaleStatus status) {

    this.id = id;
    this.userId = userId;
    this.productId = productId;
    this.size = size;
    this.price = price;
    this.date = LocalDateTime.now().plusDays(date);
    this.type = type;
    this.status = status;

  }

}
