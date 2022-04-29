package api.soldout.io.soldout.repository;

import api.soldout.io.soldout.dtos.user.UserDTO;

/**
 * .
 */

public interface UserRepository {
  UserDTO save(UserDTO user);

  UserDTO findUser(String email, String password);
}
