package api.soldout.io.soldout.controller.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *.
 */

@Getter
@NoArgsConstructor
public class SignInRequest {
  private String email;
  private String password;
}