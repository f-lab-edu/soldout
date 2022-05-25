package api.soldout.io.soldout.config;

import api.soldout.io.soldout.mapper.UserMapper;
import api.soldout.io.soldout.repository.user.MybatisUserRepository;
import api.soldout.io.soldout.repository.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * .
 */

@Configuration
public class UserConfig {

  /**
   * .
   */

  @Bean
  public UserRepository userRepository(UserMapper userMapper) {

    return new MybatisUserRepository(userMapper);

  }

}
