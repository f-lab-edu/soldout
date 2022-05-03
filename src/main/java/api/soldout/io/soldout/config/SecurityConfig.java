package api.soldout.io.soldout.config;

import api.soldout.io.soldout.service.security.SecurityService;
import api.soldout.io.soldout.service.security.SessionSecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 로그인 인증 방식을 결정하기 위한 config 파일.
 * 현재 로그인 인증 방식 : 세션 방식
 */

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityService securityService() {
    return new SessionSecurityService();
  }

}
