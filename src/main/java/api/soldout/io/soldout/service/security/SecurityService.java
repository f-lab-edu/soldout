package api.soldout.io.soldout.service.security;

import api.soldout.io.soldout.dtos.user.UserDTO;

public interface SecurityService {
  void signIn(UserDTO user);

  void logOut();
}
