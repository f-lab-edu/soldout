package api.soldout.io.soldout.config;

import api.soldout.io.soldout.dtos.ProductDto;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * .
 */

@Configuration
public class ProductConfig {

  @Value("${jackson.dataTime}")
  private String dateFormat;

  /**
   * LocalDate 클래스의 포멧을 커스텀하기 위해 주입하는 클래스.
   */

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {

    return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.serializers(

        new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat))

    );

  }

  /**
   * .
   */

  @Bean
  public Map<Long, ProductDto> productDatabase() {

    return new ConcurrentHashMap<>();

  }

  /**
   * .
   */

  @Bean
  public AtomicLong productSequence() {

    return new AtomicLong(0L);

  }

}