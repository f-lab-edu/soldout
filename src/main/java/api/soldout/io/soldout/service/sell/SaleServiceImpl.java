package api.soldout.io.soldout.service.sell;


import api.soldout.io.soldout.repository.sell.SaleRepository;
import api.soldout.io.soldout.service.sell.command.SaleBidCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * .
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

  private final SaleRepository saleRepository;

  public void saleBid(SaleBidCommand command) {

  }


}
