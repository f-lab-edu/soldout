package api.soldout.io.soldout.controller;

import api.soldout.io.soldout.dtos.user.UserDTO;
import api.soldout.io.soldout.dtos.user.request.RequestDTO;
import api.soldout.io.soldout.dtos.user.response.ResponseDTO;
import api.soldout.io.soldout.service.SecurityService;
import api.soldout.io.soldout.service.UserService;
import java.util.LinkedHashMap;
import java.util.Map;
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

  private final SecurityService securityService;

  @PostMapping("/signup")
  public ResponseEntity<ResponseDTO> signUp(@RequestBody RequestDTO request){
    UserDTO user = userService.signUp(request);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ResponseDTO.successSignUp(user));
  }

  @PostMapping("/signin")
  public ResponseEntity<ResponseDTO> signIn(@RequestBody RequestDTO request){
    UserDTO user = userService.findByIdPw(request);
    String token = securityService.createToken(user.getPassword(), (2 * 1000 * 60));
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(ResponseDTO.successSignIn(token));
  }

/*
  @GetMapping("/get/token")
  public Map<String, Object> genToken(@RequestParam(value="subject") String subject) {
    String token = securityService.createToken(subject, (2 * 1000 * 60));
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    map.put("result", token);
    return map;
  }

  @GetMapping("/get/subject")
  public Map<String, Object> getSubject(@RequestParam("token") String token) {
    String subject = securityService.getSubject(token);
    Map<String, Object> map = new LinkedHashMap<String, Object>();
    map.put("result", subject);
    return map;
  }
*/
}
