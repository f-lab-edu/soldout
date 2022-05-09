package api.soldout.io.soldout.aspect;

import static api.soldout.io.soldout.util.SecurityUtil.SESSION_ID;

import api.soldout.io.soldout.exception.NotSignInBrowserException;
import api.soldout.io.soldout.service.security.SessionSecurityService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * .
 * 로그인 검증을 위한 로직이 정의된 클래스
 */

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class SignInAspect {

  private final SessionSecurityService securityService;

  /**
   * .
   */

  @Before("@annotation(api.soldout.io.soldout.annotation.CheckSignIn)")
  public void checkSignIn() {

    RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();

    HttpSession session = ((ServletRequestAttributes) requestAttributes).getRequest().getSession();

    String sessionId = (String) session.getAttribute(SESSION_ID);

    if (!securityService.isAlreadySignInBrowser(sessionId)) {

      throw new NotSignInBrowserException("로그인한 상태가 아닙니다.");

    }

  }

}
