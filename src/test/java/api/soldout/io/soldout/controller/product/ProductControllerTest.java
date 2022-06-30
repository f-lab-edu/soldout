package api.soldout.io.soldout.controller.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import api.soldout.io.soldout.controller.request.AddProductRequest;
import api.soldout.io.soldout.controller.response.ResponseDto;
import api.soldout.io.soldout.controller.response.data.GetAllProductsData;
import api.soldout.io.soldout.domain.ImageDto;
import api.soldout.io.soldout.domain.ProductDto;
import api.soldout.io.soldout.domain.SizeInfoDto;
import api.soldout.io.soldout.enums.ProductCategory;
import api.soldout.io.soldout.interceptor.SessionSignInHandlerInterceptor;
import api.soldout.io.soldout.resolver.SignInUserArgumentResolver;
import api.soldout.io.soldout.service.product.ProductService;
import api.soldout.io.soldout.service.product.command.AddProductCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  ProductService productService;

  @MockBean
  SessionSignInHandlerInterceptor interceptor;

  @MockBean
  SignInUserArgumentResolver signInUserArgumentResolver;

  ObjectMapper objectMapper;

  // productDto 객체
  ProductCategory category = ProductCategory.SHOES;
  String name = "name";
  String brand = "brand";
  String modelNumber = "modelNumber";
  LocalDate releaseDay = LocalDate.of(22, 1, 1);
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
  void setUp() throws Exception {

    when(interceptor.preHandle(any(), any(), any()))
        .thenReturn(true);

    objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

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
  @DisplayName("제품 저장 테스트")
  void addProductTest() throws Exception {
    // given
    AddProductRequest request = new AddProductRequest(
        category, name, brand, modelNumber, releaseDay, color, sizeInfo, images
    );

    ResponseDto response = new ResponseDto(
        true, null, "상품 저장", null
    );

    ArgumentCaptor<AddProductCommand> commandCap = ArgumentCaptor.forClass(AddProductCommand.class);

    // when
    ResultActions result = mockMvc.perform(post("/products")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request))
    );

    // then
    result.andExpect(status().isOk())
          .andExpect(content().json(objectMapper.writeValueAsString(response)))
          .andDo(print());

    verify(productService, times(1)).addProduct(commandCap.capture());

    AddProductCommand command = commandCap.getValue();

    assertThat(command.getCategory()).isEqualTo(request.getCategory());
    assertThat(command.getName()).isEqualTo(request.getName());
    assertThat(command.getBrand()).isEqualTo(request.getBrand());
    assertThat(command.getColor()).isEqualTo(request.getColor());
    assertThat(command.getModelNumber()).isEqualTo(request.getModelNumber());
    assertThat(command.getReleaseDay()).isEqualTo(request.getReleaseDay());
    assertThat(command.getImages()).isEqualTo(request.getImages());
    assertThat(command.getSizeInfo()).isEqualTo(request.getSizeInfo());
  }

  @Test
  @DisplayName("전체 제품 리스트 조회 테스트")
  void findAllProductsTest() throws Exception {
    // given
    SizeInfoDto sizeInfoDto = new SizeInfoDto(1, productId, min, max, unit);

    List<ImageDto> imageDtoList = new ArrayList<>();
    imageDtoList.add(new ImageDto(1, productId, images.get(0)));
    imageDtoList.add(new ImageDto(2, productId, images.get(1)));
    imageDtoList.add(new ImageDto(3, productId, images.get(2)));

    ProductDto productDto = ProductDto.builder()
        .id(productId)
        .category(category)
        .name(name)
        .brand(brand)
        .modelNumber(modelNumber)
        .releaseDay(null) // Json Array 로 나와 에러 발생
        .color(color)
        .sizeInfo(sizeInfoDto)
        .images(imageDtoList)
        .build();

    List<ProductDto> productList = new ArrayList<>();
    productList.add(productDto);

    // when
    when(productService.findAll()).thenReturn(productList);

    ResultActions result = mockMvc.perform(get("/products"));

    // then
    ResponseDto response = new ResponseDto<>(
        true, GetAllProductsData.from(productList), "상품 리스트", null
    );

    result
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(response)))
        .andDo(print());

    verify(productService, times(1)).findAll();

  }

}