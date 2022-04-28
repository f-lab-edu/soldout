package api.soldout.io.soldout.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDTO {
  private String email;
  private String password;
}
