package api.soldout.io.soldout.service.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import api.soldout.io.soldout.domain.UserDto;
import api.soldout.io.soldout.repository.user.MybatisUserRepository;
import api.soldout.io.soldout.repository.user.UserRepository;
import api.soldout.io.soldout.service.user.command.SignUpCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  UserService userService;

  UserRepository userRepository;

  PasswordEncoder passwordEncoder;

  String email = "email";
  String password = "password";
  String name = "name";
  String phone = "010-0000-0000";
  String address = "address";

  @BeforeEach
  void init() {

    userRepository = mock(MybatisUserRepository.class);

    passwordEncoder = mock(BCryptPasswordEncoder.class);

    userService = new UserServiceImpl(userRepository, passwordEncoder);

  }

  @Test
  @DisplayName("회원가입 로직 테스트")
  void signUpTest() {
    // given
    SignUpCommand command = new SignUpCommand(email, password, name, phone, address);

    final ArgumentCaptor<UserDto> userCap = ArgumentCaptor.forClass(UserDto.class);

    // when
    when(userService.isExistEmail(command.getEmail())).thenReturn(false);

    when(passwordEncoder.encode(command.getPassword())).thenReturn(command.getPassword());

    userService.signUp(command);

    // then
    verify(userRepository).save(userCap.capture());

    UserDto user = userCap.getValue();

    assertThat(user.getEmail()).isEqualTo(command.getEmail());
    assertThat(user.getPassword()).isEqualTo(command.getPassword());
    assertThat(user.getName()).isEqualTo(command.getName());
    assertThat(user.getPhone()).isEqualTo(command.getPhone());
    assertThat(user.getAddress()).isEqualTo(command.getAddress());

  }

  @Test
  @DisplayName("이메일 및 비밀번호 일치 확인 로직 테스트")
  void checkEmailAndPwTest() {
    // given
    UserDto user = UserDto.builder()
        .email(email)
        .password(password)
        .build();

    // when
    when(userService.findByEmail(email)).thenReturn(user);

    when(passwordEncoder.matches(password, user.getPassword())).thenReturn(true);

    UserDto findUser = userService.checkEmailAndPw(email, password);

    // then
    assertThat(findUser.getEmail()).isEqualTo(user.getEmail());
    assertThat(findUser.getPassword()).isEqualTo(user.getPassword());

  }

  @Test
  @DisplayName("이메일로 회원 정보 조회 로직 테스트")
  void findByEmailTest() {
    // given
    UserDto userDto = UserDto.builder()
        .email(email)
        .build();


    // when
    when(userRepository.findByEmail(email)).thenReturn(userDto);

    UserDto findUserDto = userService.findByEmail(email);

    // then
    verify(userRepository, times(1)).findByEmail(email);

    assertThat(findUserDto.getEmail()).isEqualTo(userDto.getEmail());

  }

  @Test
  @DisplayName("이메일 존재 여부 파악 로직 테스트")
  void isExistEmailTest() {
    // given

    // when
    when(userRepository.isExistEmail(email)).thenReturn(true);

    boolean result = userService.isExistEmail(email);

    // then
    verify(userRepository, times(1)).isExistEmail(email);

    assertThat(result).isTrue();

  }

}