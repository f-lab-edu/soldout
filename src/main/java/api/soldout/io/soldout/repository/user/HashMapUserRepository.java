package api.soldout.io.soldout.repository.user;

import api.soldout.io.soldout.dtos.entity.UserDto;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;

/**
 *.
 */

@Slf4j
public class HashMapUserRepository {

  private ConcurrentHashMap<Long, UserDto> database = new ConcurrentHashMap<>();

  private AtomicLong sequence = new AtomicLong(0L);

  /**
   * .
   */

  public void save(UserDto user) {

    database.put(sequence.incrementAndGet(), user);

  }

  /**
   * .
   */

  public boolean isExistEmail(String email) {

    UserDto findUser = findByEmail(email);

    if (findUser != null) {

      return true;

    }

    return false;

  }

  /**
   * .
   */

  public UserDto findByEmail(String email) {

    for (UserDto tempUser : database.values()) {

      if (tempUser.isSameEmail(email)) {

        return tempUser;

      }

    }

    return null;

  }
}
