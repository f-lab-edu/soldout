package api.soldout.io.soldout.config;

import api.soldout.io.soldout.interceptor.JwtSignInHandlerInterceptor;
import api.soldout.io.soldout.interceptor.SessionSignInHandlerInterceptor;
import api.soldout.io.soldout.resolver.SignInUserArgumentResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 인증 로직을 위해 필요한 객체를 등록하는 구성 클래스.
 * WebMvcConfigure 인터페이스를 구현하는 이유
   * 인터셉터 등록을 통해 로그인 검증 기능을 수행하기 위해서
 */

@Configuration
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {

  private final SignInUserArgumentResolver signInUserArgumentResolver;

  @Value("${jwt.secretKey}")
  private String secretKey;

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

    registry.addInterceptor(sessionSignInHandlerInterceptor());
    // registry.addInterceptor(jwtSignInHandlerInterceptor());

  }

  /**
   * 로그인한 회원 정보를 조회하고 컨트롤러의 매개변수 받아오기 위한 리졸버.
   * .@param argumentResolvers
   */

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

    argumentResolvers.add(signInUserArgumentResolver);

  }
}
