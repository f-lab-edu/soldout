package api.soldout.io.soldout.service.user;

import api.soldout.io.soldout.dtos.user.UserDto;
import api.soldout.io.soldout.dtos.user.request.RequestSignUpDto;

/**
 *.
 */

public interface UserService {

  UserDto signUp(RequestSignUpDto request);

  UserDto findByEmail(String email);

  boolean isExistEmail(String email);

  void isValidPassword(String encodedPassword, String password);

}
