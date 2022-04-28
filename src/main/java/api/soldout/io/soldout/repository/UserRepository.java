package api.soldout.io.soldout.repository;

import api.soldout.io.soldout.dtos.UserDTO;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

//  private final DataSource dataSource;

  public UserDTO save(UserDTO user){
    // user 객체를 DB로 저장
    return user;
  }
}
