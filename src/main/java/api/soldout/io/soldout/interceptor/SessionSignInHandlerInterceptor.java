package api.soldout.io.soldout.interceptor;

import static api.soldout.io.soldout.util.SecurityUtil.SESSION_ID;

import api.soldout.io.soldout.annotation.CheckSignIn;
import api.soldout.io.soldout.exception.NotSignInBrowserException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class SessionSignInHandlerInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    HandlerMethod handlerMethod = (HandlerMethod) handler;

    CheckSignIn checkSignIn = handlerMethod.getMethodAnnotation(CheckSignIn.class);

    if (checkSignIn == null) {

      return true;

    }

    HttpSession session = request.getSession();

    if (session == null || session.getAttribute(SESSION_ID) == null) {

      throw new NotSignInBrowserException("로그인한 상태가 아닙니다.");

    }

    return true;

  }

}
