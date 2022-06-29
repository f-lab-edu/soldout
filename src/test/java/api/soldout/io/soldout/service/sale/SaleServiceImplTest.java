package api.soldout.io.soldout.service.sale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import api.soldout.io.soldout.domain.SaleDto;
import api.soldout.io.soldout.domain.SaleDto.SaleStatus;
import api.soldout.io.soldout.domain.SaleDto.SaleType;
import api.soldout.io.soldout.repository.sale.SaleRepository;
import api.soldout.io.soldout.service.sale.command.SaleBidCommand;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * .
 */

@ExtendWith(MockitoExtension.class)
class SaleServiceImplTest {

  SaleService saleService;

  SaleRepository saleRepository;

  int productId = 1;
  int userId = 1;
  int saleId = 1;
  int size = 1;
  int price = 1;
  int period = 3;
  SaleStatus status = SaleStatus.BID_PROGRESS;

  @BeforeEach
  void init() throws Exception {

    saleRepository = mock(SaleRepository.class);

    saleService = new SaleServiceImpl(saleRepository);

  }

  @Test
  @DisplayName("판매 입찰 테스트")
  void saleBidTest() throws Exception {
    // given
    ArgumentCaptor<SaleDto> captor = ArgumentCaptor.forClass(SaleDto.class);

    SaleBidCommand command =  new SaleBidCommand(
        userId,
        productId,
        size,
        price,
        period,
        SaleType.SALE_BID
    );

    // when
    saleService.saleBid(command);

    // then
    verify(saleRepository, times(1)).saveSale(captor.capture());

    SaleDto saleDto = captor.getValue();

    assertThat(saleDto.getUserId()).isEqualTo(command.getUserId());
    assertThat(saleDto.getProductId()).isEqualTo(command.getProductId());
    assertThat(saleDto.getSize()).isEqualTo(command.getSize());
    assertThat(saleDto.getPrice()).isEqualTo(command.getPrice());
    assertThat(saleDto.getType()).isEqualTo(SaleType.SALE_BID);
    assertThat(saleDto.getStatus()).isEqualTo(SaleStatus.BID_PROGRESS);
    assertThat(saleDto.getDate().getClass()).isEqualTo(LocalDateTime.class);

  }

  @Test
  @DisplayName("사용자 Id로 판매 입찰 목록 조회")
  void findByUserIdTest() throws Exception {
    // given
    SaleDto saleDto = SaleDto.builder()
        .userId(userId)
        .build();

    List<SaleDto> list = new ArrayList<>();
    list.add(saleDto);


    // when
    when(saleRepository.findByUserId(userId)).thenReturn(list);

    List<SaleDto> findList = saleService.findByUserId(userId);

    // then
    verify(saleRepository, times(1)).findByUserId(userId);

    assertThat(findList.size()).isEqualTo(list.size());

  }

  @Test
  @DisplayName("제품 Id로 판매 입찰 목록 조회")
  void findByProductIdTest() throws Exception {
    // given
    SaleDto saleDto = SaleDto.builder()
        .productId(productId)
        .build();

    List<SaleDto> list = new ArrayList<>();
    list.add(saleDto);

    // when
    when(saleRepository.findByProductId(productId)).thenReturn(list);

    List<SaleDto> findList = saleService.findByProductId(productId);

    // then
    verify(saleRepository, times(1)).findByProductId(productId);

    assertThat(findList.size()).isEqualTo(list.size());

  }

  @Test
  @DisplayName("제품 Id & 사이즈 & 가격 & 상태로 판매 입찰 목록 조회")
  void findByProductIdAndSizeAndPriceAndSaleStatusTest() throws Exception {
    // given
    SaleDto saleDto = SaleDto.builder()
        .productId(productId)
        .size(size)
        .price(price)
        .status(status)
        .build();

    List<SaleDto> list = new ArrayList<>();
    list.add(saleDto);

    // when
    when(saleRepository.findByProductIdAndSizeAndPriceAndSaleStatus(productId, size, price, status))
        .thenReturn(list);

    List<SaleDto> findList = saleService.findByProductIdAndSizeAndPriceAndSaleStatus(productId,
        size, price, status);

    // then
    verify(saleRepository, times(1))
        .findByProductIdAndSizeAndPriceAndSaleStatus(productId, size, price, status);

    assertThat(findList.size()).isEqualTo(list.size());

  }

  @Test
  @DisplayName("판매 상태 수정 로직 테스트")
  void updateSaleStatusTest() throws Exception {
    // given
    SaleStatus updateStatus = SaleStatus.MATCHING_COMPLETE;
    // when
    saleService.updateSaleStatus(saleId, updateStatus);

    // then
    verify(saleRepository, times(1)).updateSaleStatus(saleId, updateStatus);

  }
}