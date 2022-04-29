package api.soldout.io.soldout.config;

import api.soldout.io.soldout.dtos.user.UserDTO;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "api.soldout.io.soldout.user")
public class UserConfig {
  @Bean
  public ConcurrentHashMap<Long, UserDTO> database() {
    return new ConcurrentHashMap<>();
  }

  @Bean
  public AtomicLong sequence() {
    return new AtomicLong(0L);
  }
}
