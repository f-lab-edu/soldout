package api.soldout.io.soldout.service.security;

import static api.soldout.io.soldout.util.SecurityUtil.SESSION_ID;

import api.soldout.io.soldout.exception.AlreadySignInBrowserException;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Session 인증 방식에 대한 비즈니스 로직을 담당하는 서비스 객체.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class SessionSecurityService {

  private final Map<String, String> sessionDataBase;

  @Value("${session.interval}")
  private int sessionInterval;

  /**
   * .
   */

  public void signIn(String email, HttpServletRequest request) {

    HttpSession session = request.getSession();

    String sessionId = (String) session.getAttribute(SESSION_ID);

    if (isAlreadySignInBrowser(sessionId)) {

      throw new AlreadySignInBrowserException("이미 로그인한 상태입니다.");

    }

    sessionId = UUID.randomUUID().toString();

    sessionDataBase.put(sessionId, email);

    session.setAttribute(SESSION_ID, sessionId);

    session.setMaxInactiveInterval(sessionInterval);

  }

  /**
   * .
   */

  public void logOut(HttpServletRequest request) {

    HttpSession session = request.getSession();

    String sessionId = (String) session.getAttribute(SESSION_ID);

    sessionDataBase.remove(sessionId);

    session.invalidate();

  }

  /**
   * .
   */

  public boolean isAlreadySignInBrowser(String sessionId) {

    if (sessionId != null) {

      return true;

    }

    if (sessionDataBase.containsKey(sessionId)) {

      return true;

    }

    return false;

  }
}
