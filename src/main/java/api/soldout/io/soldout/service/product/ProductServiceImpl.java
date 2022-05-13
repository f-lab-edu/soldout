package api.soldout.io.soldout.service.product;

import api.soldout.io.soldout.dtos.ProductDto;
import api.soldout.io.soldout.repository.product.ProductRepository;
import api.soldout.io.soldout.service.product.command.SaveCommand;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * .
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public ProductDto save(SaveCommand command) {

    ProductDto product = ProductDto.builder()
        .name(command.getName())
        .brand(command.getBrand())
        .modelNumber(command.getModelNumber())
        .color(command.getColor())
        .releaseDay(command.getReleaseDay())
        .imagesLink(command.getImagesLink())
        .build();

    return productRepository.save(product);
  }

  @Override
  public Collection<ProductDto> findAllProduct() {

    return productRepository.findAllProduct();
  }

  @Override
  public void clear() {
    productRepository.clear();
  }
}
