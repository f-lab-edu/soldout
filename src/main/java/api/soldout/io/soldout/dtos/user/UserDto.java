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

  public UserDto(RequestSignUpDto request) {

    UserDto.builder()
        .email(request.getEmail())
        .password(request.getPassword())
        .name(request.getName())
        .phone(request.getPhone())
        .address(request.getAddress())
        .build();

  }

  /**
   *.
   */

  public boolean isExistEmail(String email) {
    if (this.getEmail().equals(email)) {
      return true;
    }
    return false;
  }

  /**
   *.
   */

  public boolean isExistPw(String password) {
    if (this.getPassword().equals(password)) {
      return true;
    }
    return false;
  }

}
