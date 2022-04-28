package api.soldout.io.soldout.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDTO {
  private String email;
  private int password;

  @Builder
  public UserDTO(String email, int password){
    this.email = email;
    this.password = password;
  }

  public static UserDTO build(String email, String password){
    return UserDTO.builder()
        .email(email)
        .password(password.hashCode())
        .build();
  }
}
