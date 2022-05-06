package api.soldout.io.soldout.service.user;

import api.soldout.io.soldout.dtos.user.UserDto;
import api.soldout.io.soldout.service.user.command.CommandSignUpDto;

/**
 *.
 */

public interface UserService {

  UserDto signUp(CommandSignUpDto commandDto);

  UserDto findByEmail(String email);

  boolean isExistEmail(String email);

  void isValidPassword(String encodedPassword, String password);

}
