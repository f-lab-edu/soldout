package api.soldout.io.soldout.service.user;

import api.soldout.io.soldout.dtos.user.UserDto;
import api.soldout.io.soldout.dtos.user.request.RequestSignUpDto;
import api.soldout.io.soldout.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDto signUp(RequestSignUpDto request) {

    String encodedPassword = passwordEncoder.encode(request.getPassword());

    UserDto user = UserDto.builder()
        .email(request.getEmail())
        .password(encodedPassword)
        .name(request.getName())
        .phone(request.getPhone())
        .address(request.getPassword())
        .build();

    return userRepository.save(user);
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

  @Override
  public boolean isValidPassword(String password, String encodedPassword) {
    if (passwordEncoder.matches(password, encodedPassword)) {
      return true;
    }
    return false;
  }
}
