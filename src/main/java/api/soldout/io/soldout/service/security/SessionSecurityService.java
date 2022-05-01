package api.soldout.io.soldout.service.security;

import api.soldout.io.soldout.dtos.user.UserDTO;
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

  private final static String SESSION_ID = "SessionId";

  private final HttpSession session;

  private ConcurrentHashMap<String, UserDTO> sessionDB = new ConcurrentHashMap<>();

  @Override
  public String signIn(UserDTO user) {
    // 세션 아이디 생성
    String sessionId = UUID.randomUUID().toString();
    // 쿠키에 세션 아이디 저장
    session.setAttribute(SESSION_ID, sessionId);
    // 세션 DB에 저징
    sessionDB.put(sessionId, user);
    return sessionId;
  }

  @Override
  public void logOut() {
    // 해당 세션 아이디로 저장된 값이 있다면 로그아웃 로직 실행
    if(session.getAttribute(SESSION_ID) != null) {
      session.invalidate();
    }
  }

  public String getUserId() {
    return sessionDB.get(session.getAttribute(SESSION_ID)).getEmail();
  }
}
