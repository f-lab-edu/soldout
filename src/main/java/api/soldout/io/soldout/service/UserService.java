package api.soldout.io.soldout.service;

import api.soldout.io.soldout.dtos.UserDTO;
import api.soldout.io.soldout.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserDTO signIn(String email, String password){
    return userRepository.save(UserDTO.build(email, password));
  }
}
