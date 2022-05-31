package api.soldout.io.soldout.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public enum ExpirationPeriod {

  THREE_DAY("EP_001", 3),
  WEEK("EP_002", 7),
  HALF_MONTH("EP_003", 15),
  MONTH("EP_004", 30);

  private final String code;
  private final int days;

}
