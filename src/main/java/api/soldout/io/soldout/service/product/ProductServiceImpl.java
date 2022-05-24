package api.soldout.io.soldout.service.product;

import api.soldout.io.soldout.dtos.ImageDto;
import api.soldout.io.soldout.dtos.ProductDto;
import api.soldout.io.soldout.repository.product.ProductRepository;
import api.soldout.io.soldout.service.product.command.AddProductCommand;
import java.util.ArrayList;
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
        .images(makeImageList(command.getImages()))
        .build();

    productRepository.save(product);

  }

  @Override
  public List<ProductDto> findAll() {

    return productRepository.findAll();

  }

  private List<ImageDto> makeImageList(List<String> images) {

    List<ImageDto> imageList = new ArrayList<>();

    for (String link : images) {

      ImageDto image = ImageDto.builder()
          .link(link)
          .build();

      imageList.add(image);

    }

    return imageList;

  }
}
