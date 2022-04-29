package api.soldout.io.soldout.user.repository;

import api.soldout.io.soldout.user.dtos.UserDTO;

/**
 * .
 */

public interface UserRepositoryInterface {
  UserDTO save(UserDTO user);

  UserDTO findUser(String email, String password);
}
