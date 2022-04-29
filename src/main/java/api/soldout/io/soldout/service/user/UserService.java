package api.soldout.io.soldout.service.user;

import api.soldout.io.soldout.dtos.user.UserDTO;
import api.soldout.io.soldout.dtos.user.request.RequestDTO;

public interface UserService {

  UserDTO signUp(RequestDTO request);

  UserDTO findByIdPw(RequestDTO request);
}
