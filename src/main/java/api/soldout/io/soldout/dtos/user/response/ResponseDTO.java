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

  public static ResponseDTO successSignUp(UserDTO user) {
    return new ResponseDTO(true, SignUpData.from(user), "회원가입 성공", null);
  }

  public static ResponseDTO successSignIn() {
    return new ResponseDTO(true, null, "로그인 성공", null);
  }

  public static ResponseDTO successLogOut() {
    return new ResponseDTO(true, null, "로그아웃 성공", null);
  }

  public static ResponseDTO successCheckEmail(boolean canUseEmail) {
    return new ResponseDTO(true, null, "사용 가능한 이메일", null);
  }

  public static ResponseDTO fail(String code, String message) {
    return new ResponseDTO(false, null, "",
        ErrorDTO.from(code, message));
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
