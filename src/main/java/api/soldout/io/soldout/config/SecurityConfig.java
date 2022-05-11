package api.soldout.io.soldout.config;

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
 * .
 * 인증 로직을 위해 필요한 객체를 등록하는 구성 클래스
 * WebMvcConfigure 인터페이스를 구현하는 이유
  - 인터셉터 등록을 통해 로그인 검증 기능을 수행
 */

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

  @Value("${session.db.expiration}")
  private long expiration;

  /**
   * .
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
   * .
   */

  @Bean
  public PasswordEncoder passwordEncoder() {

    return new BCryptPasswordEncoder();

  }

  /**
   * 로그인 인증 방법에 따라 다른 인터셉터를 사용한다.
   */

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    registry.addInterceptor(new SessionSignInHandlerInterceptor());
    // registry.addInterceptor(new JwtSignInHandlerInterceptor());

  }
}
