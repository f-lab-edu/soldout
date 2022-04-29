package api.soldout.io.soldout.dtos.user.response.data;


import api.soldout.io.soldout.dtos.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpData {
  private String email;
  private String password;
  private String name;
  private String phone;
  private String address;

  public static SignUpData from(UserDTO user){
    String email = user.getEmail();
    String  password = user.getPassword();
    String name = user.getName();
    String phone = user.getPhone();
    String address = user.getAddress();
    return new SignUpData(email, password, name, phone, address);
  }
}
