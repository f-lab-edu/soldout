package api.soldout.io.soldout.repository.user;

import api.soldout.io.soldout.dtos.UserDto;

/**
 * .
 */

public interface UserRepository {

  UserDto save(UserDto user);

  UserDto findByEmail(String email);

  boolean isExistEmail(String email);

}
