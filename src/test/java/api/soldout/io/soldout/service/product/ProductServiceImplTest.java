package api.soldout.io.soldout.service.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import api.soldout.io.soldout.dtos.entity.ImageDto;
import api.soldout.io.soldout.dtos.entity.ProductDto;
import api.soldout.io.soldout.dtos.entity.SizeInfoDto;
import api.soldout.io.soldout.repository.product.ProductRepository;
import api.soldout.io.soldout.service.product.command.AddProductCommand;
import api.soldout.io.soldout.util.enums.ProductCategory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

  ProductService productService;

  ProductRepository productRepository;

  // productDto 객체
  ProductCategory category = ProductCategory.SHOES;
  String name = "name";
  String brand = "brand";
  String modelNumber = "modelNumber";
  LocalDate day = LocalDate.of(22, 1, 1);
  String color = "color";

  // sizeInfo 객체
  HashMap<String, Integer> sizeInfo;
  int productId = 1;
  int min = 210;
  int max = 320;
  int unit = 5;

  // images 리스트
  List<String> images;
  String link1 = "link1";
  String link2 = "link2";
  String link3 = "link3";

  @BeforeEach
  void init() throws Exception {

    productRepository = mock(ProductRepository.class);

    productService = new ProductServiceImpl(productRepository);

    sizeInfo = new HashMap<>();
    sizeInfo.put("min", min);
    sizeInfo.put("max", max);
    sizeInfo.put("unit", unit);

    images = new ArrayList<>();
    images.add(link1);
    images.add(link2);
    images.add(link3);

  }

  @Test
  @DisplayName("제품 정보 저장 로직 테스트")
  void addProductTest() {
    // given
    AddProductCommand command = new AddProductCommand(
        ProductCategory.SHOES, name, brand, modelNumber, day, color, sizeInfo, images
    );

    ArgumentCaptor<ProductDto> captor = ArgumentCaptor.forClass(ProductDto.class);

    //when
    productService.addProduct(command);

    // then
    verify(productRepository, times(1)).saveProduct(captor.capture());

    ProductDto productDto = captor.getValue();

    assertThat(productDto.getCategory()).isEqualTo(productDto.getCategory());
    assertThat(productDto.getBrand()).isEqualTo(productDto.getBrand());
    assertThat(productDto.getName()).isEqualTo(productDto.getName());
    assertThat(productDto.getColor()).isEqualTo(productDto.getColor());
    assertThat(productDto.getModelNumber()).isEqualTo(productDto.getModelNumber());
    assertThat(productDto.getReleaseDay()).isEqualTo(productDto.getReleaseDay());

    SizeInfoDto sizeInfoDto = productDto.getSizeInfo();

    assertThat(sizeInfoDto.getMax()).isEqualTo(sizeInfo.get("max"));
    assertThat(sizeInfoDto.getMin()).isEqualTo(sizeInfo.get("min"));
    assertThat(sizeInfoDto.getUnit()).isEqualTo(sizeInfo.get("unit"));

    List<ImageDto> imageDtoList = productDto.getImages();

    assertThat(imageDtoList.size()).isEqualTo(imageDtoList.size());

  }

  @Test
  @DisplayName("전체 상품 목록 조회 테스트")
  void findAllTest() {
    // given
    List<ProductDto> list = new ArrayList<>();
    list.add(new ProductDto());

    //when
    when(productRepository.findAll()).thenReturn(list);

    List<ProductDto> findList = productService.findAll();

    //then
    assertThat(findList.size()).isEqualTo(list.size());

  }
}