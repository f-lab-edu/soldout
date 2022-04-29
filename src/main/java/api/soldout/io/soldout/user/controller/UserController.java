package api.soldout.io.soldout.user.controller;

import api.soldout.io.soldout.user.dtos.request.RequestDTO;
import api.soldout.io.soldout.user.dtos.response.ResponseDTO;
import api.soldout.io.soldout.user.dtos.UserDTO;
import api.soldout.io.soldout.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * .
 */

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/signin")
  public ResponseEntity<ResponseDTO> signIn(@RequestBody RequestDTO request){
    UserDTO user = userService.signIn(request);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ResponseDTO.successSignIn(user));
  }

  @PostMapping("/signup")
  public ResponseEntity<ResponseDTO> signUp(@RequestBody RequestDTO request){
    UserDTO user = userService.signUp(request);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ResponseDTO.successSignUp(user));
  }
}
