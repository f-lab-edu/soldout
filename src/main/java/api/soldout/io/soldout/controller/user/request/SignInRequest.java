package api.soldout.io.soldout.controller.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *.
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {
  private String email;
  private String password;
}
