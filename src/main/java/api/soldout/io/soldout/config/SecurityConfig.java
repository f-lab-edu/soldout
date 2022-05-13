package api.soldout.io.soldout.config;

import api.soldout.io.soldout.interceptor.JwtSignInHandlerInterceptor;
import api.soldout.io.soldout.interceptor.SessionSignInHandlerInterceptor;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 인증 로직을 위해 필요한 객체를 등록하는 구성 클래스.
 * WebMvcConfigure 인터페이스를 구현하는 이유
   * 인터셉터 등록을 통해 로그인 검증 기능을 수행하기 위해서
 */

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

  @Value("${session.db.expiration}")
  private long expiration;

  @Value("${jwt.secretKey}")
  private String secretKey;

  /**
   * 세션정보를 저장할 DB 객체.
   * Redis 대체 컬렉션
   */

  @Bean
  public Map<String, String> sessionDataBase() {

    return ExpiringMap.builder()
        .maxSize(1000)
        .expirationPolicy(ExpirationPolicy.CREATED)
        .expiration(expiration, TimeUnit.SECONDS)
        .build();
  }

  /**
   * 회원 패스워드를 DB에 저장할 때, 암호화를 담당.
   */

  @Bean
  public PasswordEncoder passwordEncoder() {

    return new BCryptPasswordEncoder();

  }

  /**
   * 세션 인증 방식 사용시 로그인 검증을 담당하는 인터셉터 객체.
   */

  @Bean
  public SessionSignInHandlerInterceptor sessionSignInHandlerInterceptor() {

    return new SessionSignInHandlerInterceptor();

  }

  /**
   * Jwt 인증 방식 사용시 로그인 검증을 담당하는 인터셉터 객체.
   * 객체 빈 등록 단계에서 secretKey 를 주입받는다.
  */

  @Bean
  public JwtSignInHandlerInterceptor jwtSignInHandlerInterceptor() {

    return new JwtSignInHandlerInterceptor(secretKey);

  }

  /**
   * 로그인 인증 방법에 따라 다른 인터셉터를 사용한다.
   */

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    //registry.addInterceptor(sessionSignInHandlerInterceptor());
    registry.addInterceptor(jwtSignInHandlerInterceptor());

  }
}
