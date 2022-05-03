package api.soldout.io.soldout.dtos.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *.
 */

@Getter
@NoArgsConstructor
public class RequestSignInDto {
  private String email;
  private String password;
}
