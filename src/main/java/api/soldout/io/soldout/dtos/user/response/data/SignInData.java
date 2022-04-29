package api.soldout.io.soldout.dtos.user.response.data;

import api.soldout.io.soldout.dtos.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInData {
  private String password;

  public static SignInData from(String token){
    return new SignInData(token);
  }
}