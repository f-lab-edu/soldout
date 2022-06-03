package api.soldout.io.soldout.controller.sell;

import api.soldout.io.soldout.service.sell.SellService;
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
public class SellController {

  private final SellService sellService;

  @PostMapping()
  public void sell() {

  }

}
