package api.soldout.io.soldout.repository.user;

import api.soldout.io.soldout.domain.UserDto;
import api.soldout.io.soldout.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * .
 */

@Slf4j
@RequiredArgsConstructor
public class MybatisUserRepository implements UserRepository {

  private final UserMapper userMapper;

  @Override
  public void save(UserDto user) {

    userMapper.save(user);

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
