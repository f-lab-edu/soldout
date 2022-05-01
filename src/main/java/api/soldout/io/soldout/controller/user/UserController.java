package api.soldout.io.soldout.controller.user;

import api.soldout.io.soldout.dtos.user.UserDTO;
import api.soldout.io.soldout.dtos.user.request.RequestDTO;
import api.soldout.io.soldout.dtos.user.response.ResponseDTO;
import api.soldout.io.soldout.service.security.SecurityService;
import api.soldout.io.soldout.service.user.UserServiceImpl;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * .
 */

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserServiceImpl userService;

  private final SecurityService securityService;

  @PostMapping("/signup")
  public ResponseEntity<ResponseDTO> signUp(@RequestBody RequestDTO request){
    UserDTO user = userService.save(request);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ResponseDTO.successSignUp(user));
  }

  @PostMapping("/signin")
  public ResponseEntity<ResponseDTO> signIn(@RequestBody RequestDTO request){
    // 회원 조회
    UserDTO user = userService.findByIdPw(request.getEmail(), request.getPassword());
    // 회원 로그인
    String sessionId = securityService.signIn(user);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ResponseDTO.successSignIn(sessionId));
  }
}
