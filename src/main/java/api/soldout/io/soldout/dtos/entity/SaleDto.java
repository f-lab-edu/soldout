package api.soldout.io.soldout.dtos.entity;

import api.soldout.io.soldout.util.enums.SaleType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * .
 */

@Getter
@NoArgsConstructor
public class SaleDto {

  /**
   * .
   */

  @Getter
  @AllArgsConstructor
  public enum SaleStatus {

    SALE_PROGRESS("입찰 진행중"),
    SALE_SIGNED("거래 완료");

    private final String text;

  }

  private int id;
  private int userId;
  private int productId;
  private int size;
  private int price;
  private LocalDateTime day;
  private SaleType type;
  private SaleStatus status;

  /**
   * .
   *
   */

  @Builder
  public SaleDto(int id, int userId, int productId, int size, int price, int day,
                 SaleType type, SaleStatus status) {

    this.id = id;
    this.userId = userId;
    this.productId = productId;
    this.size = size;
    this.price = price;
    this.day = LocalDateTime.now().plusDays(day);
    this.type = type;
    this.status = status;

  }

}
