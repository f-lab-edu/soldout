package api.soldout.io.soldout.user.dtos;

import api.soldout.io.soldout.user.dtos.request.RequestDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDTO {
  private String email;
  private int password;
  private String name;
  private String phone;
  private String address;

  @Builder
  public UserDTO(String email, int password, String name, String phone, String address){
    this.email = email;
    this.password = password;
    this.name = name;
    this.phone = phone;
    this.address = address;
  }

  public static UserDTO buildUser(RequestDTO request){
    return UserDTO.builder()
        .email(request.getEmail())
        .password(request.getPassword().hashCode())
        .name(request.getName())
        .phone(request.getPhone())
        .address(request.getAddress())
        .build();
  }

  public boolean isValid(String email, String password){
    if (this.getEmail().equals(email) && this.getPassword() == password.hashCode()){
      return true;
    }
    return false;
  }
}
