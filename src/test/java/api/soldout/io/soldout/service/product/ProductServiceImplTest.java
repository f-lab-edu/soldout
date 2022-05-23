package api.soldout.io.soldout.service.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import api.soldout.io.soldout.dtos.ProductDto;
import api.soldout.io.soldout.repository.product.ProductRepository;
import api.soldout.io.soldout.service.product.command.AddProductCommand;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private ProductServiceImpl productService;

  List<String> imagesLink = new ArrayList<>();
  AddProductCommand command;

  List<ProductDto> list = new ArrayList<>();
  ProductDto product;

  @BeforeEach
  void setUp() {
    // 이미지 링크 데이터 추가
    imagesLink.add("test1");
    imagesLink.add("test2");
    imagesLink.add("test3");

    // Command 객체 생성
    command = new AddProductCommand(
        "NIKE DUNK LOW BLACK",
        "NIKE",
        "DD1391-100",
        LocalDate.of(2021, 1, 14),
        "BLACK/WHITE",
        imagesLink
    );

    // Product 객체 생성
    product = ProductDto.builder()
        .name(command.getName())
        .brand(command.getBrand())
        .modelNumber(command.getModelNumber())
        .releaseDay(command.getReleaseDay())
        .color(command.getColor())
        // imageDto 리스트 생성
        .build();

    // Products 목록을 위한 List 추가 -> findAllProducts 리턴값
    list.add(product);

  }

  @Test
  @DisplayName("상품 목록 저장")
  void addProduct() {

    productService.addProduct(command);

  }

  @Test
  @DisplayName("전체 상품 리스트 찾기")
  void findAllProduct() {

    when(productRepository.findAll()).thenReturn(list);

    assertThat(productService.findAll().size()).isEqualTo(1);

  }

}