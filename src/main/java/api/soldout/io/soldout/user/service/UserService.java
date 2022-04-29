package api.soldout.io.soldout.user.service;

import api.soldout.io.soldout.user.dtos.request.RequestDTO;
import api.soldout.io.soldout.user.dtos.UserDTO;
import api.soldout.io.soldout.user.repository.UserRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepositoryInterface userRepository;

  public UserDTO signIn(RequestDTO request){
    // 유효성 검사 진행 후 일치한 객체가 있다면 리턴. 아니면 예외처리
    return userRepository.findUser(request.getEmail(), request.getPassword());
  }

  public UserDTO signUp(RequestDTO request){
    return userRepository.save(UserDTO.buildUser(request));}
}
