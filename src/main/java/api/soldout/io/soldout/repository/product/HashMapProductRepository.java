package api.soldout.io.soldout.repository.product;


import api.soldout.io.soldout.dtos.ProductDto;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * .
 */

@Slf4j
@Repository
@RequiredArgsConstructor
public class HashMapProductRepository implements ProductRepository {

  private final Map<Long, ProductDto> productDatabase;

  private final AtomicLong productSequence;

  @Override
  public ProductDto save(ProductDto product) {

    productDatabase.put(productSequence.incrementAndGet(), product);

    return productDatabase.get(productSequence.get());

  }

  @Override
  public List<ProductDto> findAll() {

    return (List<ProductDto>) productDatabase.values();

  }

}
