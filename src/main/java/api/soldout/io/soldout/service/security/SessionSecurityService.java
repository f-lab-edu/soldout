package api.soldout.io.soldout.service.security;

import api.soldout.io.soldout.dtos.user.UserDTO;
import api.soldout.io.soldout.exception.AlreadySignInUserException;
import api.soldout.io.soldout.exception.NotExistSignInUserException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Session 인증 방식에 대한 비즈니스 로직을 담당하는 서비스 객체.
 */

@Service
@RequiredArgsConstructor
public class SessionSecurityService implements SecurityService{

  private final HttpSession session;

  private ConcurrentHashMap<String, UserDTO> sessionDB = new ConcurrentHashMap<>();

  private final static String SESSION_ID = "SESSION_ID";

  @Override
  public void signIn(UserDTO user) {
    // 세션 아이디가 이미 존재할 경우, 예외처리
    if (session.getAttribute(SESSION_ID) != null) {

      throw new AlreadySignInUserException("이미 로그인된 회원입니다");

    }
    // 세션 아이디 생성
    String sessionId = UUID.randomUUID().toString();
    // 쿠키에 세션 아이디 저장
    session.setAttribute(SESSION_ID, sessionId);
    // 세션 DB에 저장
    sessionDB.put(sessionId, user);
  }

  @Override
  public void logOut() {
    // 생성된 session Id가 없는 경우, 예외처리
    if(session.getAttribute(SESSION_ID) == null) {

      throw new NotExistSignInUserException("로그인한 회원이 아닙니다.");

    }

    session.invalidate();
  }
}
