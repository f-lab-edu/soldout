package api.soldout.io.soldout.interceptor;

import static api.soldout.io.soldout.util.SecurityUtil.TOKEN_ID;

import api.soldout.io.soldout.annotation.CheckSignIn;
import api.soldout.io.soldout.exception.NotSignInBrowserException;
import api.soldout.io.soldout.exception.NotValidTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * .
 */

@Slf4j
public class JwtSignInHandlerInterceptor implements HandlerInterceptor {

  private String secretKey;

  /**
   * .
   */

  public JwtSignInHandlerInterceptor(String secretKey) {

    this.secretKey = secretKey;

  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    HandlerMethod handlerMethod = (HandlerMethod) handler;

    CheckSignIn checkSignIn = handlerMethod.getMethodAnnotation(CheckSignIn.class);

    if (checkSignIn == null) {

      return true;

    }

    String token = request.getHeader(TOKEN_ID);

    if (token == null) {

      throw new NotSignInBrowserException("로그인한 상태가 아닙니다.");

    }

    checkTokenValid(token);

    return true;

  }

  private boolean checkTokenValid(String token) {

    byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secretKey);

    Key signingKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());

    try {

      Jwts.parserBuilder()
          .setSigningKey(signingKey)
          .build()
          .parseClaimsJws(token)
          .getBody();

      return true;

    } catch (ExpiredJwtException e) {

      throw new NotValidTokenException("토큰 유효기간이 만료되었습니다.");

    } catch (JwtException e) {

      throw new NotValidTokenException("유효한 토큰이 아닙니다.");

    } catch (RuntimeException e) {

      throw new NotValidTokenException("예상치 못한 토큰 검증 에러");

    }

  }

}
