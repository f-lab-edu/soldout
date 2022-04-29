package api.soldout.io.soldout.repository.user;

import api.soldout.io.soldout.dtos.user.UserDTO;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class HashMapUserRepository implements UserRepository {

  private ConcurrentHashMap<Long, UserDTO> database = new ConcurrentHashMap<>();
  private AtomicLong sequence = new AtomicLong(0L);

  @Override
  public UserDTO save(UserDTO user){
    database.put(sequence.incrementAndGet(), user);
    return database.get(sequence.get());
  }

  @Override
  public UserDTO findUser(String email, String password) {
    for (UserDTO tempUser : database.values()) {
      if(tempUser.isValid(email, password)) {
        return tempUser;
      }
    }
    throw new NullPointerException("No User");
  }
}