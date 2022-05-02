package api.soldout.io.soldout.dtos.user;

import api.soldout.io.soldout.dtos.user.request.RequestSignUpDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserDTO {
  private String email;
  private String password;
  private String name;
  private String phone;
  private String address;

  public static UserDTO buildUser(RequestSignUpDTO request){

    return UserDTO.builder()
        .email(request.getEmail())
        .password(request.getPassword())
        .name(request.getName())
        .phone(request.getPhone())
        .address(request.getAddress())
        .build();

  }

  public boolean isExistId(String email){
    if (this.getEmail().equals(email)) {
      return true;
    }
    return false;
  }

  public boolean isExistIdPw(String email, String password){
    if (this.getEmail().equals(email) && this.getPassword().equals(password)){
      return true;
    }
    return false;
  }
}
