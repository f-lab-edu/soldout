package api.soldout.io.soldout.service.sale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import api.soldout.io.soldout.repository.sale.SaleRepository;
import api.soldout.io.soldout.service.sale.command.SaleBidCommand;
import api.soldout.io.soldout.util.enums.SaleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * .
 */

@ExtendWith(MockitoExtension.class)
class SaleServiceImplTest {

  @Mock
  SaleRepository saleRepository;

  SaleService saleService;

  @BeforeEach
  void init() throws Exception {

    saleService = new SaleServiceImpl(saleRepository);

  }

  @Test
  @DisplayName("판매 입찰 테스트")
  void saleBidTest() throws Exception {
    // given
    SaleBidCommand command =  new SaleBidCommand(
        1,
        1,
        220,
        100000,
        3,
        SaleType.SALE_BID
    );

    // when
    saleService.saleBid(command);

    // then
    verify(saleRepository).saveSale(any());
    verify(saleRepository, times(1)).saveSale(any());

  }

  @Test
  @DisplayName("사용자 Id로 판매 입찰 목록 조회")
  void findByUserIdTest() throws Exception {
    // given
    int userId = 1;

    // when
    saleService.findByUserId(userId);

    // then
    verify(saleRepository).findByUserId(userId);
    verify(saleRepository, times(1)).findByUserId(userId);

  }

  @Test
  @DisplayName("사용자 Id로 판매 입찰 목록 조회")
  void findByProductIdTest() throws Exception {
    // given
    int productId = 1;

    // when
    saleService.findByProductId(productId);

    // then
    verify(saleRepository).findByProductId(productId);
    verify(saleRepository, times(1)).findByProductId(productId);

  }
}