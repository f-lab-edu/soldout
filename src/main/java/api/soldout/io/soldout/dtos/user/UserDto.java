package api.soldout.io.soldout.dtos.user;

import api.soldout.io.soldout.dtos.user.request.RequestSignUpDto;
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

  public static UserDto buildUser(RequestSignUpDto request, String encodedPassword) {

    return UserDto.builder()
            .email(request.getEmail())
            .password(encodedPassword)
            .name(request.getName())
            .phone(request.getPhone())
            .address(request.getAddress())
            .build();
  }

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
