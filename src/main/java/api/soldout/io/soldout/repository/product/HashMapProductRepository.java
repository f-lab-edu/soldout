package api.soldout.io.soldout.repository.product;


import api.soldout.io.soldout.dtos.entity.ProductDto;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * .
 */

@Slf4j
@RequiredArgsConstructor
public class HashMapProductRepository {

  private final Map<Long, ProductDto> productDatabase;

  private final AtomicLong productSequence;

  /**
   * .
   */

  public void save(ProductDto product) {

    productDatabase.put(productSequence.incrementAndGet(), product);

  }

  /**
   * .
   */

  public List<ProductDto> findAll() {

    return (List<ProductDto>) productDatabase.values();

  }

}
