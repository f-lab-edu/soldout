package api.soldout.io.soldout.controller.user;

import api.soldout.io.soldout.dtos.user.UserDto;
import api.soldout.io.soldout.dtos.user.request.RequestSignInDto;
import api.soldout.io.soldout.dtos.user.request.RequestSignUpDto;
import api.soldout.io.soldout.dtos.user.response.ResponseDto;
import api.soldout.io.soldout.dtos.user.response.data.SignUpData;
import api.soldout.io.soldout.service.security.SecurityService;
import api.soldout.io.soldout.service.user.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
 * securityService : 로그인, 로그아웃을 위한 세션 관리를 담당
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
  public ResponseDto signUp(@RequestBody RequestSignUpDto requestDto) {

    UserDto user = userService.signUp(requestDto);

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
  public ResponseDto signIn(HttpServletRequest request,
                            HttpServletResponse response,
                            @RequestBody RequestSignInDto requestDto) {

    UserDto user = userService.findByEmail(requestDto.getEmail());

    userService.isValidPassword(requestDto.getPassword(), user.getPassword());

    securityService.signIn(user.getEmail(), request, response);

    return new ResponseDto(true, null, "로그인 성공", null);

  }

  /**
   *.
   */

  @PostMapping("/logout")
  public ResponseDto logOut(HttpServletRequest request, HttpServletResponse response) {

    securityService.logOut(request, response);

    return new ResponseDto(true, null, "로그아웃 성공", null);

  }
}
