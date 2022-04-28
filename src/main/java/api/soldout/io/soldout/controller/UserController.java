package api.soldout.io.soldout.controller;

import api.soldout.io.soldout.controller.request.RequestDTO;
import api.soldout.io.soldout.controller.response.ResponseDTO;
import api.soldout.io.soldout.dtos.UserDTO;
import api.soldout.io.soldout.service.UserService;
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
    UserDTO user = userService.signIn(request.getEmail(), request.getPassword());
    if (user.getEmail().equals("test@test.com")) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ResponseDTO.success(user));
    } else {
      return ResponseEntity
          .status(HttpStatus.UNAUTHORIZED)
          .body(ResponseDTO.fail());
    }
  }

}
