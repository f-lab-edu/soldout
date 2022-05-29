package api.soldout.io.soldout.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * .
 */

@Getter
@Builder
@AllArgsConstructor
public class OrderDto {

  private int id;
  private int userId;
  private int productId;
  private int size;
  private int price;
  private String type;
  private EndDay endDay;

  /**
   * .
   */

  public enum OrderType {

    ORDER_NOW,
    ORDER_BID;

  }

  /**
   * .
   */

  public enum EndDay {

    THREE_DAY(3),
    WEEK(7),
    HALF_MONTH(15),
    MONTH(30);

    private final int days;

    EndDay(int days) {

      this.days = days;

    }

    /**
     * .
     */

    public int getDays() {

      return days;

    }

  }

}
