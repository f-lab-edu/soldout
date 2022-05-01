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

  private final static String USER_ID = "userId";

  private final HttpSession session;

  private ConcurrentHashMap<String, UserDTO> sessionDB = new ConcurrentHashMap<>();

  public String signIn(UserDTO user) {
    // 세션 아이디 생성
    String sessionId = UUID.randomUUID().toString();
    // 쿠키에 세션 아이디 저장
    session.setAttribute(USER_ID, sessionId);
    // 세션 DB에 저
    sessionDB.put(sessionId, user);
    return sessionId;
  }
}
