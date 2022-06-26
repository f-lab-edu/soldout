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

import api.soldout.io.soldout.controller.product.request.AddProductRequest;
import api.soldout.io.soldout.dtos.entity.ImageDto;
import api.soldout.io.soldout.dtos.entity.ProductDto;
import api.soldout.io.soldout.dtos.entity.SizeInfoDto;
import api.soldout.io.soldout.dtos.response.ResponseDto;
import api.soldout.io.soldout.dtos.response.data.GetAllProductsData;
import api.soldout.io.soldout.interceptor.SessionSignInHandlerInterceptor;
import api.soldout.io.soldout.resolver.SignInUserArgumentResolver;
import api.soldout.io.soldout.service.product.ProductService;
import api.soldout.io.soldout.service.product.command.AddProductCommand;
import api.soldout.io.soldout.util.enums.ProductCategory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

  @BeforeEach
  void setUp() throws Exception {

    when(interceptor.preHandle(any(), any(), any()))
        .thenReturn(true);

    objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

  }

  @Test
  @DisplayName("제품 저장 테스트")
  void addProductTest() throws Exception {
    // given
    HashMap<String, Integer> sizeInfo = new HashMap<>();
    sizeInfo.put("min", 0);
    sizeInfo.put("max", 10);
    sizeInfo.put("unit", 5);

    List<String> images = new ArrayList<>();
    images.add("testLink1");
    images.add("testLink2");
    images.add("testLink3");

    AddProductRequest request = new AddProductRequest(
        ProductCategory.SHOES,
        "testName",
        "testBrand",
        "testModelNumber",
        LocalDate.now(),
        "testColor",
        sizeInfo,
        images
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
    int productId = 1;

    SizeInfoDto sizeInfoDto = new SizeInfoDto(1, productId, 210, 320, 5);

    List<ImageDto> imageDtoList = new ArrayList<>();
    imageDtoList.add(new ImageDto(1, productId, "testLink1"));
    imageDtoList.add(new ImageDto(2, productId, "testLink2"));
    imageDtoList.add(new ImageDto(3, productId, "testLink3"));

    ProductDto productDto = ProductDto.builder()
        .id(productId)
        .category(ProductCategory.SHOES)
        .name("testName")
        .brand("testBrand")
        .modelNumber("testModelNumber")
        .releaseDay(null) // Json Array 로 나와
        .color("testColor")
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