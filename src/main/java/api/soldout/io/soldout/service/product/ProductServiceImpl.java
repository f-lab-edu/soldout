package api.soldout.io.soldout.service.product;

import api.soldout.io.soldout.dtos.entity.ProductDto;
import api.soldout.io.soldout.repository.product.ProductRepository;
import api.soldout.io.soldout.service.product.command.AddProductCommand;
import java.util.List;
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
  public void addProduct(AddProductCommand command) {

    ProductDto product = ProductDto.builder()
        .name(command.getName())
        .brand(command.getBrand())
        .modelNumber(command.getModelNumber())
        .color(command.getColor())
        .releaseDay(command.getReleaseDay())
        .build();

    product.buildToImageDto(command.getImages());

    productRepository.save(product);

  }

  @Override
  public List<ProductDto> findAll() {

    return productRepository.findAll();

  }
}
