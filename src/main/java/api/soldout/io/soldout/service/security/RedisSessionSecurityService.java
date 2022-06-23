package api.soldout.io.soldout.service.security;


import api.soldout.io.soldout.exception.AlreadySignInBrowserException;
import api.soldout.io.soldout.exception.NotSignInBrowserException;
import api.soldout.io.soldout.util.SecurityUtil;
import java.time.Duration;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * .
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisSessionSecurityService implements SecurityService {

  private final HttpSession session;

  private final RedisTemplate<String, Object> redisTemplate;

  @Value("${spring.redis.session.timeout}")
  private int timeout;

  @Override
  public void signIn(int userId) {

    if (isAlreadySignInBrowser(getCurrentSessionId())) {

      throw new AlreadySignInBrowserException("이미 로그인한 상태입니다.");

    }

    String token = UUID.randomUUID().toString();

    redisTemplate.opsForValue().set(token, String.valueOf(userId));

    redisTemplate.expire(String.valueOf(userId), Duration.ofSeconds(timeout));

    session.setAttribute(SecurityUtil.SESSION_ID, token);

    session.setMaxInactiveInterval(timeout);

  }

  @Override
  public void logOut() {

    if (!isAlreadySignInBrowser(getCurrentSessionId())) {

      throw new NotSignInBrowserException("로그인한 상태가 아닙니다.");

    }

    redisTemplate.delete(getCurrentSessionId());

    session.invalidate();

  }

  @Override
  public int getCurrentUserId() {

    String userId = (String) redisTemplate.opsForValue().get(getCurrentSessionId());

    if (userId == null) {

      throw new NotSignInBrowserException("로그인한 회원 정보가 없습니다.");

    }

    return Integer.parseInt(userId);

  }

  /**
   * .
   */

  private String getCurrentSessionId() {

    return (String) session.getAttribute(SecurityUtil.SESSION_ID);

  }

  /**
   * .
   */

  private boolean isAlreadySignInBrowser(String sessionId) {

    if (sessionId == null) {

      return false;

    } else if (redisTemplate.opsForValue().get(sessionId) == null) {

      return false;

    }

    return true;

  }
}
