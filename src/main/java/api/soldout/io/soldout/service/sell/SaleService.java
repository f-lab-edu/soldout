package api.soldout.io.soldout.service.sell;

import api.soldout.io.soldout.service.sell.command.SaleBidCommand;

/**
 * .
 */

public interface SaleService {

  void saleBid(SaleBidCommand command);

}
