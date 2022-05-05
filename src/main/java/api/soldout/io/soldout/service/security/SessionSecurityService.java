package api.soldout.io.soldout.service.security;

import static api.soldout.io.soldout.util.SecurityUtil.SESSION_ID;

import api.soldout.io.soldout.exception.AlreadySignInUserException;
import api.soldout.io.soldout.exception.NotSignInUserException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Session 인증 방식에 대한 비즈니스 로직을 담당하는 서비스 객체.
 */

@Service(value = "securityService")
@RequiredArgsConstructor
public class SessionSecurityService implements SecurityService {

  private final ConcurrentHashMap<String, String> sessionDadaBase;

  @Override
  public void signIn(String email, HttpServletRequest request, HttpServletResponse response) {

    HttpSession session = request.getSession();

    if (isExistSessionId(session)) {

      throw new AlreadySignInUserException("이미 로그인된 회원입니다");

    }

    String sessionId = UUID.randomUUID().toString();

    session.setAttribute(SESSION_ID, sessionId);

    sessionDadaBase.put(sessionId, email);
  }

  @Override
  public void logOut(HttpServletRequest request, HttpServletResponse response) {

    HttpSession session = request.getSession();

    if (!isExistSessionId(session)) {

      throw new NotSignInUserException("로그인한 회원이 아닙니다.");

    }

    session.invalidate();

  }

  private boolean isExistSessionId(HttpSession session) {

    if (session.getAttribute(SESSION_ID) == null) {

      return false;

    }

    return true;

  }
}
