package api.soldout.io.soldout.dtos.user.response;

import api.soldout.io.soldout.dtos.user.UserDTO;
import api.soldout.io.soldout.dtos.user.response.data.SignInData;
import api.soldout.io.soldout.dtos.user.response.data.SignUpData;
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
    return new ResponseDTO(true, SignUpData.from(user), "SignUp", null);
  }

  public static ResponseDTO successSignIn(){
    return new ResponseDTO(true, null, "SignIn", null);
  }

  public static ResponseDTO successLogOut(){
    return new ResponseDTO(true, null, "LogOut", null);
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
