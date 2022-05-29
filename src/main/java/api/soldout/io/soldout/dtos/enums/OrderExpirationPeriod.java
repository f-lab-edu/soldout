package api.soldout.io.soldout.dtos.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public enum OrderExpirationPeriod {

  THREE_DAY("OEP_001", 3),
  WEEK("OEP_002", 7),
  HALF_MONTH("OEP_003", 15),
  MONTH("OEP_004", 30);

  private final String code;
  private final int days;

  /**
   * .
   */

  @JsonCreator
  public static OrderExpirationPeriod fromCode(String code) {

    for (OrderExpirationPeriod tempDays : OrderExpirationPeriod.values()) {

      if (tempDays.getCode().equals(code)) {

        return tempDays;

      }

    }

    return null;

  }

}
