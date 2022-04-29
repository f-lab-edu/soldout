package api.soldout.io.soldout.service;

import api.soldout.io.soldout.dtos.user.request.RequestDTO;
import api.soldout.io.soldout.dtos.user.UserDTO;
import api.soldout.io.soldout.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserDTO signUp(RequestDTO request){
    return userRepository.save(UserDTO.buildUser(request));
  }

  public UserDTO findByIdPw(RequestDTO request){
    return userRepository.findUser(request.getEmail(), request.getPassword());
  }
}
