package api.soldout.io.soldout.service.security;

import static api.soldout.io.soldout.util.SecurityUtil.SESSION_ID;

import api.soldout.io.soldout.exception.AlreadySignInBrowserException;
import api.soldout.io.soldout.exception.NotSignInBrowserException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Session 인증 방식에 대한 비즈니스 로직을 담당하는 서비스 객체.
 */

@Service
@RequiredArgsConstructor
public class SessionSecurityService {

  private final ConcurrentHashMap<String, String> sessionDataBase;

  @Value("${sessionInterval}")
  private int sessionInterval;

  /**
   * .
   */

  public void signIn(String email, HttpServletRequest request) {

    HttpSession session = request.getSession();

    if (isAlreadySignInBrowser(session)) {

      throw new AlreadySignInBrowserException("이미 로그인 되어있는 브라우저입니다.");

    }

    String sessionId = UUID.randomUUID().toString();

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

    if (!isAlreadySignInBrowser(session)) {

      throw new NotSignInBrowserException("로그인 되어있지 않은 브라우저입니다.");

    }

    sessionDataBase.remove(sessionId);

    session.invalidate();

  }

  private boolean isAlreadySignInBrowser(HttpSession session) {

    String sessionId = (String) session.getAttribute(SESSION_ID);

    if (sessionId == null) {

      return false;

    }

    return true;

  }
}
