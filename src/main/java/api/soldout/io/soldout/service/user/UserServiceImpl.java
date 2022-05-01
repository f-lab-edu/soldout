package api.soldout.io.soldout.service.user;

import api.soldout.io.soldout.dtos.user.request.RequestSignUpDTO;
import api.soldout.io.soldout.dtos.user.UserDTO;
import api.soldout.io.soldout.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;

  @Override
  public UserDTO save(RequestSignUpDTO request){

    return userRepository.save(UserDTO.buildUser(request));

  }

  @Override
  public UserDTO findByIdPw(String email, String password){

    return userRepository.findByIdPw(email, password);

  }
}
