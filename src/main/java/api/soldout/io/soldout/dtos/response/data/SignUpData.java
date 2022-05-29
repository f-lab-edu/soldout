package api.soldout.io.soldout.dtos.response.data;


import api.soldout.io.soldout.dtos.entity.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *.
 */

@Getter
@AllArgsConstructor
public class SignUpData {

  private String email;
  private String name;
  private String phone;
  private String address;

  /**
   *.
   */

  public static SignUpData from(UserDto user) {

    String email = user.getEmail();
    String name = user.getName();
    String phone = user.getPhone();
    String address = user.getAddress();

    return new SignUpData(email, name, phone, address);
  }
}
