package api.soldout.io.soldout.config;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * .
 */

@Configuration
public class SecurityConfig {

  @Value("${session.db.expiration}")
  private long expiration;

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
  public Map<String, String> sessionDataBase() {

    return ExpiringMap.builder()
        .maxSize(1000)
        .expirationPolicy(ExpirationPolicy.CREATED)
        .expiration(expiration, TimeUnit.SECONDS)
        .build();
  }
}
