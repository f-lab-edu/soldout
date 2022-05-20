package api.soldout.io.soldout.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 *.
 */

@Getter
@Builder
@AllArgsConstructor
public class UserDto {

  private String email;
  private String password;
  private String name;
  private String phone;
  private String address;

  /**
   *.
   */

  public boolean isSameEmail(String email) {

    if (this.getEmail().equals(email)) {

      return true;

    }

    return false;

  }
}
