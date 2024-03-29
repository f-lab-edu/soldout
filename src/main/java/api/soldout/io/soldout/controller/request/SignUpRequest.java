package api.soldout.io.soldout.controller.request;

import api.soldout.io.soldout.service.user.command.SignUpCommand;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

  @NotBlank
  private String email;
  @NotBlank
  @Length(max = 15)
  private String password;
  @NotBlank
  private String name;
  @NotBlank
  private String phone;
  @NotBlank
  private String address;

  public static SignUpCommand toCommand(SignUpRequest requestDto) {
    return new SignUpCommand(
      requestDto.getEmail(),
      requestDto.getPassword(),
      requestDto.getName(),
      requestDto.getPhone(),
      requestDto.getAddress()
    );
  }
}
