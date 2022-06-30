package api.soldout.io.soldout.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public class ResponseDto<T> {

  private boolean success;
  private T data;
  private String message;
  private Error error;

  /**
   * .
   */

  @Getter
  @AllArgsConstructor
  public static class Error {

    private String code;
    private String message;

    /**
     *.
     */

    public static Error from(String code, String message) {

      return new Error(code, message);

    }
  }
}
