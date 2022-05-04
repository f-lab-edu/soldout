package api.soldout.io.soldout.config;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * .
 */

@Configuration
public class SecurityConfig {

  /**
   * .
   */

  @Bean
  public PasswordEncoder passwordEncoder() {

    return new BCryptPasswordEncoder();

  }

  /**
   * .
   */

  @Bean
  public ConcurrentHashMap<String, String> sessionDataBase() {

    return new ConcurrentHashMap<>();

  }
}
