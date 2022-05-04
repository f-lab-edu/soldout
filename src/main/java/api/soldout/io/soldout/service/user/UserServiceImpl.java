package api.soldout.io.soldout.service.user;

import api.soldout.io.soldout.dtos.user.UserDto;
import api.soldout.io.soldout.dtos.user.request.RequestSignUpDto;
import api.soldout.io.soldout.exception.NotValidEmailException;
import api.soldout.io.soldout.exception.NotValidPasswordException;
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

    UserDto user = UserDto.buildUser(request, encodedPassword);

    return userRepository.save(user);

  }

  @Override
  public boolean isExistEmail(String email) {

    return userRepository.isExistEmail(email);

  }

  @Override
  public UserDto findByEmail(String email) {

    UserDto user = userRepository.findByEmail(email);

    if (user == null) {

      throw new NotValidEmailException("이메일을 잘못 입력했습니다.");

    }

    return user;

  }

  @Override
  public void isValidPassword(String password, String encodedPassword) {

    boolean validPassword = passwordEncoder.matches(password, encodedPassword);

    if (!validPassword) {

      throw new NotValidPasswordException("비밀번호를 잘못 입력했습니다.");

    }
  }
}
