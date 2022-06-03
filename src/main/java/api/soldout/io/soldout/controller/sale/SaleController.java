package api.soldout.io.soldout.controller.sale;

import api.soldout.io.soldout.service.sell.SaleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * .
 */

@Slf4j
@RestController
@RequestMapping("/sell")
@RequiredArgsConstructor
public class SaleController {

  private final SaleService saleService;

}
