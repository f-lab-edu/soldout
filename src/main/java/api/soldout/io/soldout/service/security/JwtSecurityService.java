package api.soldout.io.soldout.service.security;

import static api.soldout.io.soldout.util.SecurityUtil.TOKEN_ID;

import api.soldout.io.soldout.exception.AlreadySignInBrowserException;
import api.soldout.io.soldout.exception.NotSignInBrowserException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * .
 */

@Slf4j
@Service
public class JwtSecurityService implements SecurityService {

  @Value("${jwt.secretKey}")
  private String secretKey;

  @Value("${jwt.ttlMillis}")
  private int ttlMillis;

  /**
   * .
   */

  public void signIn(String email) {

    HttpServletRequest request = getCurrentRequest();

    HttpServletResponse response = getCurrentResponse();

    if (request.getHeader(TOKEN_ID) != null) {

      throw new AlreadySignInBrowserException("이미 로그인된 회원입니다");

    }

    String token = createToken(email);

    response.setHeader(TOKEN_ID, token);

  }

  /**
   * .
   */

  public void logOut() {

    HttpServletRequest request = getCurrentRequest();

    if (request.getHeader(TOKEN_ID) == null) {

      throw new NotSignInBrowserException("로그인한 회원이 아닙니다.");

    }

    // 토큰의 유효기간을 0으로 만들어 로그아웃 기능 구현

  }

  private ServletRequestAttributes getRequestAttributes() {

    return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

  }

  private HttpServletRequest getCurrentRequest() {

    return getRequestAttributes().getRequest();

  }

  private HttpServletResponse getCurrentResponse() {

    return getRequestAttributes().getResponse();

  }

  private String createToken(String email) {

    if (ttlMillis <= 0) {

      throw new RuntimeException("Expiry time must be greater than Zero : [" + ttlMillis + "] ");

    }

    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secretKey);

    Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

    return Jwts.builder()
        .setSubject(email)
        .signWith(signingKey, signatureAlgorithm)
        .setExpiration(new Date(System.currentTimeMillis() + ttlMillis))
        .compact();

  }

  private String decodingToken(String token) {

    Claims claims = Jwts.parserBuilder()
        .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
        .build()
        .parseClaimsJws(token)
        .getBody();

    return claims.getSubject();

  }

}
