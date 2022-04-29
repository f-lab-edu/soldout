package api.soldout.io.soldout.user.dtos.response.data;


import api.soldout.io.soldout.user.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpData {
  private String email;
  private int password;
  private String name;
  private String phone;
  private String address;

  public static SignUpData from(UserDTO user){
    String email = user.getEmail();
    int password = user.getPassword();
    String name = user.getName();
    String phone = user.getPhone();
    String address = user.getAddress();
    return new SignUpData(email, password, name, phone, address);
  }
}
