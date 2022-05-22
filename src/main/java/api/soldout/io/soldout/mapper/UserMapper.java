package api.soldout.io.soldout.mapper;

import api.soldout.io.soldout.dtos.UserDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * .
 */

@Mapper
public interface UserMapper {

  void save(UserDto user);

  UserDto findByEmail(String email);

  boolean isExistEmail(String email);

}
