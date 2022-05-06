package api.soldout.io.soldout.service.user.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *.
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommandSignUpDto {

  private String email;
  private String password;
  private String name;
  private String phone;
  private String address;

}
