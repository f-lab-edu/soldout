package api.soldout.io.soldout.service.user;

import api.soldout.io.soldout.dtos.user.request.RequestDTO;
import api.soldout.io.soldout.dtos.user.UserDTO;
import api.soldout.io.soldout.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;

  @Override
  public UserDTO signUp(RequestDTO request){
    return userRepository.save(UserDTO.buildUser(request));
  }

  @Override
  public UserDTO findByIdPw(RequestDTO request){
    return userRepository.findUser(request.getEmail(), request.getPassword());
  }
}
