package api.soldout.io.soldout.service.security;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

/**
 * .
 */

public interface SecurityService {
  void signIn(String email, HttpSession session);

  void logOut(HttpSession session);
}
