package api.soldout.io.soldout.repository.user;

import api.soldout.io.soldout.dtos.UserDto;
import api.soldout.io.soldout.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * .
 */

@Slf4j
@Repository
@RequiredArgsConstructor
public class MybatisUserRepository implements UserRepository {

  private final UserMapper userMapper;

  @Override
  public UserDto save(UserDto user) {

    return userMapper.save(user);

  }

  @Override
  public UserDto findByEmail(String email) {

    return userMapper.findByEmail(email);

  }

  @Override
  public boolean isExistEmail(String email) {

    return userMapper.isExistEmail(email);

  }
}
