package api.soldout.io.soldout.controller.user.request;

import api.soldout.io.soldout.service.user.command.SignUpCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *.
 */

@Getter
@NoArgsConstructor
public class RequestSignUpDto {

  private String email;
  private String password;
  private String name;
  private String phone;
  private String address;

  /**
   * .
   */

  public static SignUpCommand toCommand(RequestSignUpDto requestDto) {
    return new SignUpCommand(
      requestDto.getEmail(),
      requestDto.getPassword(),
      requestDto.getName(),
      requestDto.getPhone(),
      requestDto.getAddress()
    );
  }
}
