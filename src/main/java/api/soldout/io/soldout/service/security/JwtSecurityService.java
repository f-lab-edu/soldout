package api.soldout.io.soldout.service.security;

import api.soldout.io.soldout.dtos.user.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * JWT 방식의 Service 구현체.
 * 추후 인증방식 변경 가능성을 가지고 있다고 가정하여 구현체의 기본 형식만 작성
 */

// @Service
public class JwtSecurityService implements SecurityService {

  private static final String SECRET_KEY = "aasjjkjaskjdl1k2naskjkdakj34c8sasdfwefwefw156434wegwef";
  private static final long TTL_MILLIS = (2 * 1000 * 60);

  @Override
  public void signIn(UserDTO user) {
    if (TTL_MILLIS <= 0) {
      throw new RuntimeException("Expiry time must be greater than Zero : ["+TTL_MILLIS+"] ");
    }
    // 토큰을 서명하기 위해 사용해야할 알고리즘 선택
    SignatureAlgorithm signatureAlgorithm= SignatureAlgorithm.HS256;
    byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
    Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
    // return 할 경우, 토큰 문자열이 리턴된다. 세션 방식은 리턴값이 불필요해서 "void"로 임시 변경한 상태
    Jwts.builder()
        .setSubject(user.getEmail())
        .signWith(signingKey, signatureAlgorithm)
        .setExpiration(new Date(System.currentTimeMillis()+TTL_MILLIS))
        .compact();
  }

  @Override
  public void logOut(){
  }

  public String getUserId(String token) {
    Claims claims = Jwts.parserBuilder()
        .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
        .build()
        .parseClaimsJws(token)
        .getBody();
    return claims.getSubject();
  }
}
