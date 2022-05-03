package api.soldout.io.soldout.repository.user;

import api.soldout.io.soldout.dtos.user.UserDTO;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class HashMapUserRepository implements UserRepository {

  private ConcurrentHashMap<Long, UserDTO> database = new ConcurrentHashMap<>();

  private AtomicLong sequence = new AtomicLong(0L);

  @Override
  public UserDTO save(UserDTO user){

    if (isExistEmail(user)) {
      return null;
    }

    database.put(sequence.incrementAndGet(), user);

    return database.get(sequence.get());
  }

  @Override
  public UserDTO findByEmail(String email) {

    for (UserDTO tempUser : database.values()) {

      if(tempUser.isExistEmail(email)) {
        return tempUser;
      }

    }
    return null;
  }

  private boolean isExistEmail(UserDTO user) {
    if (database.size() != 0 &&
        findByEmail(user.getEmail()) != null) {
      return true;
    }
    return false;
  }
}
