package api.soldout.io.soldout.service.security;

import static api.soldout.io.soldout.util.SessionUtil.SESSION_ID;

import api.soldout.io.soldout.exception.AlreadySignInUserException;
import api.soldout.io.soldout.exception.NotSignInUserException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Session 인증 방식에 대한 비즈니스 로직을 담당하는 서비스 객체.
 */

@Service(value = "securityService")
@RequiredArgsConstructor
public class SessionSecurityService implements SecurityService {

  private ConcurrentHashMap<String, String> sessionDadaBase = new ConcurrentHashMap<>();

  @Override
  public void signIn(String email, HttpSession session) {

    // 세션 아이디가 이미 존재하는 경우
    if (session.getAttribute(SESSION_ID) != null) {
      throw new AlreadySignInUserException("이미 로그인된 회원입니다");
    }

    // 세션 아이디 생성
    String sessionId = UUID.randomUUID().toString();

    // 쿠키에 세션 아이디 저장
    session.setAttribute(SESSION_ID, sessionId);

    // 세션 DB에 저장
    sessionDadaBase.put(sessionId, email);
  }

  @Override
  public void logOut(HttpSession session) {

    // 세션 아이디가 존재하지 않는 경우 -> 로그인하지 않는 경우
    if (session.getAttribute(SESSION_ID) == null) {
      throw new NotSignInUserException("로그인한 회원이 아닙니다.");
    }

    // 세션 아이디 삭제
    session.invalidate();

  }
}
