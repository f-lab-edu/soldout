package api.soldout.io.soldout.user.dtos.response.data;

import api.soldout.io.soldout.user.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInData {
  private int password;

  public static SignInData from(UserDTO user){
    int password = user.getPassword();
    return new SignInData(password);
  }
}
