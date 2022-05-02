package api.soldout.io.soldout.service.user;

import api.soldout.io.soldout.dtos.user.request.RequestSignUpDTO;
import api.soldout.io.soldout.dtos.user.UserDTO;
import api.soldout.io.soldout.exception.NotExistUserException;
import api.soldout.io.soldout.exception.AlreadyExistEmailException;
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

    // 이미 존재하는 이메일일 경우, 예외처리
    if (user == null) {
      throw new AlreadyExistEmailException("이미 가입된 이메일입니다.");
    }

    return user;
  }

  @Override
  public UserDTO findByIdPw(String email, String password){

    UserDTO user = userRepository.findByIdPw(email, password);

    // 회원 조회 실패시, 예외 처리
    if (user == null) {
      throw new NotExistUserException("가입된 회원이 아닙니다.");
    }

    return user;
  }
}
