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

    UserDTO user = userRepository.save(UserDTO.buildUser(request));

    return user;
  }

  @Override
  public UserDTO findByEmail(String email) {

    UserDTO user = userRepository.findByEmail(email);

    return user;
  }
}
