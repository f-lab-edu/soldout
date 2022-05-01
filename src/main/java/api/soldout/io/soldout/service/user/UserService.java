package api.soldout.io.soldout.service.user;

import api.soldout.io.soldout.dtos.user.UserDTO;
import api.soldout.io.soldout.dtos.user.request.RequestSignUpDTO;

public interface UserService {

  UserDTO save(RequestSignUpDTO request);

  UserDTO findByIdPw(String email, String password);
}
