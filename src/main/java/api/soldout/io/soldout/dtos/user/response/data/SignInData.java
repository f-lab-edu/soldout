package api.soldout.io.soldout.dtos.user.response.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInData {
  private String email;

  public static SignInData from(String email){
    return new SignInData(email);
  }
}
