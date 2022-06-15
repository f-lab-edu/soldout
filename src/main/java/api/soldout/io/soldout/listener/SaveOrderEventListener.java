package api.soldout.io.soldout.listener;

import api.soldout.io.soldout.listener.event.SaveOrderEvent;
import api.soldout.io.soldout.service.trade.TradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * .
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveOrderEventListener implements ApplicationListener<SaveOrderEvent> {

  private final TradeService tradeService;

  @Override
  public void onApplicationEvent(SaveOrderEvent event) {

    tradeService.matchTradeByOrder(

        event.getId(), event.getProductId(), event.getSize(), event.getPrice()

    );

  }

}
