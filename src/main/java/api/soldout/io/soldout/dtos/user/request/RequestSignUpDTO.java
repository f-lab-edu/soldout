package api.soldout.io.soldout.dtos.user.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestSignUpDTO {
  private String email;
  private String password;
  private String name;
  private String phone;
  private String address;
}
