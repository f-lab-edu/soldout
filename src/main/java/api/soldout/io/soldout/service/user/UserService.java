package api.soldout.io.soldout.service.user;

import api.soldout.io.soldout.dtos.user.UserDto;
import api.soldout.io.soldout.dtos.user.request.RequestSignUpDto;

/**
 *.
 */

public interface UserService {

  UserDto save(RequestSignUpDto request);

  UserDto findByEmail(String email);

  boolean isExistEmail(String email);
}
