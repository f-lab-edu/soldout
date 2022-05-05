package api.soldout.io.soldout.service.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * .
 */

public interface SecurityService {

  void signIn(String email, HttpServletRequest request, HttpServletResponse response);

  void logOut(HttpServletRequest request, HttpServletResponse response);

}
