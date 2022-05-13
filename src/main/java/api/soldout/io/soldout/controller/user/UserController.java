package api.soldout.io.soldout.controller.user;

import api.soldout.io.soldout.annotation.CheckSignIn;
import api.soldout.io.soldout.controller.user.request.SignInRequest;
import api.soldout.io.soldout.controller.user.request.SignUpRequest;
import api.soldout.io.soldout.dtos.UserDto;
import api.soldout.io.soldout.dtos.response.ResponseDto;
import api.soldout.io.soldout.dtos.response.data.SignUpData;
import api.soldout.io.soldout.service.security.SecurityService;
import api.soldout.io.soldout.service.user.UserServiceImpl;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * userService : 회원 정보에 대한 비즈니스 로직을 담당.

 * SessionSecurityService : 세션 방식의 인증 로직 구현
 * JwtSecurityService : 토큰 방식의 인증 로직 구현

 */

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserServiceImpl userService;

  private final SecurityService securityService;

  /**
   *.
   */

  @PostMapping("/signup")
  public ResponseDto signUp(@Valid @RequestBody SignUpRequest requestDto) {

    UserDto user = userService.signUp(SignUpRequest.toCommand(requestDto));

    return new ResponseDto(true, SignUpData.from(user), "회원가입 성공", null);

  }

  /**
   *.
   */

  @GetMapping("/{email}/exists")
  public ResponseDto checkEmail(@PathVariable String email) {

    boolean existEmail = userService.isExistEmail(email);

    if (existEmail) {

      return new ResponseDto(true, null, "이미 존재하는 이메일", null);

    }

    return new ResponseDto(true, null, "사용 가능한 이메일", null);

  }

  /**
   *.
   */

  @PostMapping("/signin")
  public ResponseDto signIn(@Valid @RequestBody SignInRequest requestDto) {

    UserDto user = userService.signIn(requestDto.getEmail(), requestDto.getPassword());

    securityService.signIn(user.getEmail());

    return new ResponseDto(true, null, "로그인 성공", null);

  }

  /**
   *.
   */

  @PostMapping("/logout")
  public ResponseDto logOut() {

    securityService.logOut();

    return new ResponseDto(true, null, "로그아웃 성공", null);

  }

  /**
   * .
   */

  @GetMapping("/signin/check")
  @CheckSignIn
  public ResponseDto signInCheck() {

    return new ResponseDto(true, null, "로그인 체크", null);

  }
}
