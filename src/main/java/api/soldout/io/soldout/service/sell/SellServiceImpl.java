package api.soldout.io.soldout.service.sell;


import api.soldout.io.soldout.repository.sell.SellRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * .
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class SellServiceImpl implements SellService {

  private final SellRepository sellRepository;

}
