package api.soldout.io.soldout.config;

import api.soldout.io.soldout.domain.ProductDto;
import api.soldout.io.soldout.mapper.ProductMapper;
import api.soldout.io.soldout.repository.product.MybatisProductRepository;
import api.soldout.io.soldout.repository.product.ProductRepository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * .
 */

@Configuration
public class ProductConfig {

  /**
   * .
   */

  @Bean
  public ProductRepository productRepository(ProductMapper productMapper) {

    return new MybatisProductRepository(productMapper);

  }

  /**
   * .
   */

  @Bean
  public Map<Long, ProductDto> productDatabase() {

    return new ConcurrentHashMap<>();

  }

  /**
   * .
   */

  @Bean
  public AtomicLong productSequence() {

    return new AtomicLong(0L);

  }

}