package api.soldout.io.soldout.controller.response;

import api.soldout.io.soldout.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public class ResponseDTO<T> {
  private boolean success;
  private T data;
  private String message;
  private ErrorDTO error;

  public static ResponseDTO success(UserDTO user){
    return new ResponseDTO(true, null, "Ok", null); // data -> tokenMessage
  }

  public static ResponseDTO fail() {
    return new ResponseDTO(true, null, "",
        ErrorDTO.from("error code", "error message"));
  }

  /**
   * .
   */

  @Getter
  @AllArgsConstructor
  static class ErrorDTO{
    private String code;
    private String message;

    public static ErrorDTO from(String code, String message){
      return new ErrorDTO(code, message);
    }
  }
}
