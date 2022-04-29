package api.soldout.io.soldout.user.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDTO {
  private String email;
  private String password;
  private String name;
  private String phone;
  private String address;
}
