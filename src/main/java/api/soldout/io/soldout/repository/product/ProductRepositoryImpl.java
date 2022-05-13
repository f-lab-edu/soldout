package api.soldout.io.soldout.repository.product;


import api.soldout.io.soldout.dtos.ProductDto;
import java.util.Collection;
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
public class ProductRepositoryImpl implements ProductRepository {

  private final Map<Long, ProductDto> productDatabase;

  private final AtomicLong productSequence;

  @Override
  public ProductDto save(ProductDto product) {

    productDatabase.put(productSequence.incrementAndGet(), product);

    return productDatabase.get(productSequence.get());

  }

  @Override
  public Collection<ProductDto> findAllProduct() {

    return productDatabase.values();

  }

  @Override
  public void clear() {
    productSequence.set(0L);
    productDatabase.clear();
  }
}
