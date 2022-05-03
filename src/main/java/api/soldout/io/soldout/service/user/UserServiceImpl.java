package api.soldout.io.soldout.service.user;

import api.soldout.io.soldout.dtos.user.UserDto;
import api.soldout.io.soldout.dtos.user.request.RequestSignUpDto;
import api.soldout.io.soldout.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public UserDto save(RequestSignUpDto request) {

    UserDto user = userRepository.save(UserDto.buildUser(request));

    return user;
  }

  @Override
  public UserDto findByEmail(String email) {

    UserDto user = userRepository.findByEmail(email);

    return user;
  }

  @Override
  public boolean isExistEmail(String email) {

    return userRepository.isExistEmail(email);

  }
}
