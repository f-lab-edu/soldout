package api.soldout.io.soldout.exception.hadler;

import api.soldout.io.soldout.controller.user.UserController;
import api.soldout.io.soldout.dtos.user.response.ResponseDto;
import api.soldout.io.soldout.dtos.user.response.ResponseDto.Error;
import api.soldout.io.soldout.exception.AlreadySignInUserException;
import api.soldout.io.soldout.exception.NotSignInUserException;
import api.soldout.io.soldout.exception.NotValidEmailException;
import api.soldout.io.soldout.exception.NotValidPasswordException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *.
 */

@RestControllerAdvice(basePackageClasses = UserController.class)
public class UserExceptionHandler {

  /**
   * .
   */

  @ExceptionHandler(NotValidEmailException.class)
  public ResponseDto notValidEmailException(NotValidEmailException e) {

    return fail("Unauthorized", e.getMessage());

  }

  /**
   * .
   */

  @ExceptionHandler(NotValidPasswordException.class)
  public ResponseDto notValidPasswordException(NotValidPasswordException e) {

    return fail("Unauthorized", e.getMessage());

  }

  /**
   * .
   */

  @ExceptionHandler(AlreadySignInUserException.class)
  public ResponseDto alreadySignInUserException(AlreadySignInUserException e) {

    return fail("Unauthorized", e.getMessage());

  }

  /**
   * .
   */

  @ExceptionHandler(NotSignInUserException.class)
  public ResponseDto notSignInUserException(NotSignInUserException e) {

    return fail("Unauthorized", e.getMessage());

  }

  /**
   * .
   */

  private ResponseDto fail(String code, String message) {

    return new ResponseDto(false, null, "에러 발생", Error.from(code, message));

  }
}
