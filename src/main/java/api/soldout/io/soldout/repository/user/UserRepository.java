package api.soldout.io.soldout.repository.user;

import api.soldout.io.soldout.dtos.user.UserDTO;

/**
 * .
 */

public interface UserRepository {
  UserDTO save(UserDTO user);

  UserDTO findByIdPw(String email, String password);

  UserDTO findById(String email);
}
