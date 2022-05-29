package api.soldout.io.soldout.repository.user;

import api.soldout.io.soldout.dtos.entity.UserDto;

/**
 * .
 */

public interface UserRepository {

  void save(UserDto user);

  UserDto findByEmail(String email);

  boolean isExistEmail(String email);

}
