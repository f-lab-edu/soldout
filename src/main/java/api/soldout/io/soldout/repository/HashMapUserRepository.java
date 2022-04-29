package api.soldout.io.soldout.repository;

import api.soldout.io.soldout.dtos.user.UserDTO;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HashMapUserRepository implements UserRepository {

  private final ConcurrentHashMap<Long, UserDTO> database;
  private final AtomicLong sequence;

  public UserDTO save(UserDTO user){
    database.put(sequence.incrementAndGet(), user);
    return database.get(sequence.get());
  }

  public UserDTO findUser(String email, String password) {
    for (UserDTO tempUser : database.values()) {
      if(tempUser.isValid(email, password)) {
        return tempUser;
      }
    }
    throw new NullPointerException("No User");
  }
}
