package api.soldout.io.soldout.service.user;

import api.soldout.io.soldout.dtos.user.UserDTO;
import api.soldout.io.soldout.dtos.user.request.RequestDTO;

public interface UserService {

  UserDTO save(RequestDTO request);

  UserDTO findByIdPw(String email, String password);
}
