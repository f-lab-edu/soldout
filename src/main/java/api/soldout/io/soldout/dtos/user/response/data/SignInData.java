package api.soldout.io.soldout.dtos.user.response.data;

import api.soldout.io.soldout.service.security.SecurityService;
import api.soldout.io.soldout.service.security.SessionSecurityService;
import javax.servlet.http.HttpSession;
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
