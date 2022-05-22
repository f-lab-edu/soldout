package api.soldout.io.soldout.repository.user;

import api.soldout.io.soldout.dtos.UserDto;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 *.
 */

@Slf4j
@Repository
public class HashMapUserRepository implements UserRepository {

  private ConcurrentHashMap<Long, UserDto> database = new ConcurrentHashMap<>();

  private AtomicLong sequence = new AtomicLong(0L);

  @Override
  public void save(UserDto user) {

    database.put(sequence.incrementAndGet(), user);

  }

  @Override
  public boolean isExistEmail(String email) {

    UserDto findUser = findByEmail(email);

    if (findUser != null) {

      return true;

    }

    return false;

  }

  @Override
  public UserDto findByEmail(String email) {

    for (UserDto tempUser : database.values()) {

      if (tempUser.isSameEmail(email)) {

        return tempUser;

      }

    }

    return null;

  }
}
