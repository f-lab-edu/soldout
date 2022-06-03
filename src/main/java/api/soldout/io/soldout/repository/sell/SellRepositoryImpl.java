package api.soldout.io.soldout.repository.sell;

import api.soldout.io.soldout.mapper.SellMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * .
 */

@Slf4j
@Repository
@RequiredArgsConstructor
public class SellRepositoryImpl implements  SellRepository {

  private SellMapper sellMapper;

}
