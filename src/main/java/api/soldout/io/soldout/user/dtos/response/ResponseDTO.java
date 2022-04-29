package api.soldout.io.soldout.user.dtos.response;

import api.soldout.io.soldout.user.dtos.UserDTO;
import api.soldout.io.soldout.user.dtos.response.data.SignInData;
import api.soldout.io.soldout.user.dtos.response.data.SignUpData;
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

  public static ResponseDTO successSignUp(UserDTO user){
    return new ResponseDTO(true, SignUpData.from(user), "Ok", null);
  }

  // data -> tokenMessage
  public static ResponseDTO successSignIn(UserDTO user){
    return new ResponseDTO(true, SignInData.from(user), "Ok", null);
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
