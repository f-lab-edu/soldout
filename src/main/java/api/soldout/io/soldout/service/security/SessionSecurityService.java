package api.soldout.io.soldout.service.security;

import static api.soldout.io.soldout.util.SecurityUtil.SESSION_ID;

import api.soldout.io.soldout.exception.AlreadySignInBrowserException;
import api.soldout.io.soldout.exception.NotSignInBrowserException;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Session 인증 방식에 대한 비즈니스 로직을 담당하는 서비스 객체.

 * HttpSession
    * 톰캣에선 클라이언트로부터 받아오는 요청에 세션의 존재가 있을 경우에만 HttpSession 객체를 내부적으로 생성한다.
    * 이를 `@Autowired`를 통해 주입할 경우에는 set 혹은 getAttribute() 메소드가 호출되는 시점에 session 객체를 요청 및 생성한다.
    * 또한, 스프링에서 HttpSession 클래스는 Session Scope를 가진다.(브라우저와 서버의 연결이 유지되는 동안에만 존재)
    * 이를 싱글톤 스코프인 service layer에서 주입받게 될 경우, 주입 시점에 세션이 없는 경우도 있을 수 있다.
    * 그래서 스프링은 세션 객체에 대한 Scoped Proxy 객체를 일단 주입해주고, 요청에 따라 생성된 세션 객체를 프록시를 통해 사용할 수 있게끔 해준다.
    * 이러한 세션 관리는 내부적으로 Manager 인터페이스를 구현한 클래스에 의해 관리된다.(리스트로 저장해두고 생성, 삭제등 관리)
 **/

@Slf4j
@Service
@RequiredArgsConstructor
public class SessionSecurityService implements SecurityService {

  private final Map<String, String> sessionDataBase;

  private final HttpSession session;

  @Value("${session.interval}")
  private int sessionInterval;

  /**
   * .
   */

  public void signIn(String email) {

    if (isAlreadySignInBrowser(getCurrentSessionId())) {

      throw new AlreadySignInBrowserException("이미 로그인한 상태입니다.");

    }

    String sessionId = UUID.randomUUID().toString();

    sessionDataBase.put(sessionId, email);

    session.setAttribute(SESSION_ID, sessionId);

    session.setMaxInactiveInterval(sessionInterval);

  }

  /**
   * .
   */

  public void logOut() {

    String sessionId = getCurrentSessionId();

    if (!isAlreadySignInBrowser(sessionId)) {

      throw new NotSignInBrowserException("로그인한 상태가 아닙니다. -> session");

    }

    sessionDataBase.remove(sessionId);

    session.invalidate();

  }

  /**
   * .
   */

  private String getCurrentSessionId() {

    return (String) session.getAttribute(SESSION_ID);

  }

  /**
   * .
   */

  private boolean isAlreadySignInBrowser(String sessionId) {

    if (sessionId != null) {

      return true;

    }

    if (sessionDataBase.containsKey(sessionId)) {

      return true;

    }

    return false;

  }

}
