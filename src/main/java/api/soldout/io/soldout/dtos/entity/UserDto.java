package api.soldout.io.soldout.dtos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *.
 */

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private int id;
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
