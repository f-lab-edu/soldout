package api.soldout.io.soldout.controller.user;

import static api.soldout.io.soldout.util.SessionUtil.SESSION_ID;

import api.soldout.io.soldout.dtos.user.UserDTO;
import api.soldout.io.soldout.dtos.user.request.RequestSignInDTO;
import api.soldout.io.soldout.dtos.user.request.RequestSignUpDTO;
import api.soldout.io.soldout.dtos.user.response.ResponseDTO;
import api.soldout.io.soldout.dtos.user.response.data.SignUpData;
import api.soldout.io.soldout.exception.AlreadyExistEmailException;
import api.soldout.io.soldout.exception.AlreadySignInUserException;
import api.soldout.io.soldout.exception.NotSignInUserException;
import api.soldout.io.soldout.exception.NotValidEmailException;
import api.soldout.io.soldout.exception.NotValidPasswordException;
import api.soldout.io.soldout.service.security.SecurityService;
import api.soldout.io.soldout.service.user.UserServiceImpl;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    UserDTO user = userService.save(request);

    // 이미 존재하는 이메일일 경우, 예외처리
    if (user == null) {
      throw new AlreadyExistEmailException("이미 가입된 이메일입니다.");
    }

    return new ResponseDTO(true, SignUpData.from(user), "회원가입 성공", null);
  }

  @PostMapping("/signin")
  public ResponseDTO signIn(@RequestBody RequestSignInDTO request, HttpSession session) {

    UserDTO user = userService.findByEmail(request.getEmail());

    // 확인한 이메일로 가입된 회원이 없는 경우
    if (user == null) {
      throw new NotValidEmailException("잘못 입력된 이메일입니다.");
    }

    // 이메일은 맞지만 사용자가 입력한 비밀번호가 다를 경우
    if (!user.isExistPw(request.getPassword())) {
      throw new NotValidPasswordException("잘못 입력된 비밀번호 입니다.");
    }

    securityService.signIn(user.getEmail(), session);

    return new ResponseDTO(true, null, "로그인 성공", null);
  }

  @PostMapping("/logout")
  public ResponseDTO logOut(HttpSession session) {

    securityService.logOut(session);

    return new ResponseDTO(true, null, "로그아웃 성공", null);
  }
}
