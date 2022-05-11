package api.soldout.io.soldout.interceptor;

import static api.soldout.io.soldout.util.SecurityUtil.TOKEN_ID;

import api.soldout.io.soldout.annotation.CheckSignIn;
import api.soldout.io.soldout.exception.NotSignInBrowserException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * .
 */

public class JwtSignInHandlerInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    HandlerMethod handlerMethod = (HandlerMethod) handler;

    CheckSignIn checkSignIn = handlerMethod.getMethodAnnotation(CheckSignIn.class);

    if (checkSignIn == null) {

      return true;

    }

    if (request.getHeader(TOKEN_ID) == null) {

      throw new NotSignInBrowserException("로그인한 상태가 아닙니다.");

    }

    return true;

  }

}
