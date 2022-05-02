package api.soldout.io.soldout.controller.user;

import api.soldout.io.soldout.dtos.user.UserDTO;
import api.soldout.io.soldout.dtos.user.request.RequestSignInDTO;
import api.soldout.io.soldout.dtos.user.request.RequestSignUpDTO;
import api.soldout.io.soldout.dtos.user.response.ResponseDTO;
import api.soldout.io.soldout.service.security.SecurityService;
import api.soldout.io.soldout.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * userService : 회원 정보에 대한 비즈니스 로직을 담당
 * securityService : 로그인, 로그아웃을 위한 세션 관리를 담당
 */

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserServiceImpl userService;

  private final SecurityService securityService;

  @PostMapping("/signup")
  public ResponseDTO signUp(@RequestBody RequestSignUpDTO request) {
    // 회원 가입
    UserDTO user = userService.save(request);

    return ResponseDTO.successSignUp(user);
  }

  @PostMapping("/signin")
  public ResponseDTO signIn(@RequestBody RequestSignInDTO request) {
    // 회원 조회
    UserDTO user = userService.findByIdPw(request.getEmail(), request.getPassword());
    // 회원 로그인
    securityService.signIn(user);

    return ResponseDTO.successSignIn();
  }

  @PostMapping("/logout")
  public ResponseDTO logOut() {
    // 로그아웃
    securityService.logOut();

    return ResponseDTO.successLogOut();
  }
}
