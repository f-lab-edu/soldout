package api.soldout.io.soldout.service.security;

import api.soldout.io.soldout.dtos.user.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Value;

/**
 * JWT 방식의 Service 구현체.
 * 추후 인증방식 변경 가능성을 가지고 있다고 가정하여 구현체의 기본 형식만 작성
 */

public class JwtSecurityService implements SecurityService {

  @Value("${SECRET_KEY}")
  private static String SECRET_KEY;

  @Value("${TTL_MILLIS}")
  private static long TTL_MILLIS;

  @Override
  public void signIn(String email, HttpSession session) {
    if (TTL_MILLIS <= 0) {
      throw new RuntimeException("Expiry time must be greater than Zero : [" + TTL_MILLIS + "] ");
    }
    // 토큰을 서명하기 위해 사용해야할 알고리즘 선택
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
    Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
    // 토큰 생성
    String token = Jwts.builder()
        .setSubject(email)
        .signWith(signingKey, signatureAlgorithm)
        .setExpiration(new Date(System.currentTimeMillis() + TTL_MILLIS))
        .compact();
    // 세션에 저장
    session.setAttribute("TOKEN", token);
  }

  @Override
  public void logOut(HttpSession session) {

    session.invalidate();

  }
  /*
  public String getUserId(String token) {
    Claims claims = Jwts.parserBuilder()
        .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
        .build()
        .parseClaimsJws(token)
        .getBody();
    return claims.getSubject();
  }
  */
}
