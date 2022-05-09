package api.soldout.io.soldout.exception.hadler;

import api.soldout.io.soldout.controller.user.UserController;
import api.soldout.io.soldout.dtos.user.response.ResponseDto;
import api.soldout.io.soldout.dtos.user.response.ResponseDto.Error;
import api.soldout.io.soldout.exception.AlreadyExistEmailException;
import api.soldout.io.soldout.exception.AlreadySignInBrowserException;
import api.soldout.io.soldout.exception.NotSignInBrowserException;
import api.soldout.io.soldout.exception.NotValidEmailException;
import api.soldout.io.soldout.exception.NotValidPasswordException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

  @ExceptionHandler(AlreadyExistEmailException.class)
  public ResponseDto alreadyExistEmailException(AlreadyExistEmailException e) {

    return fail("Unauthorized", e.getMessage());

  }

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

  @ExceptionHandler(AlreadySignInBrowserException.class)
  public ResponseDto alreadySignInUserException(AlreadySignInBrowserException e) {

    return fail("Unauthorized", e.getMessage());

  }

  /**
   * .
   */

  @ExceptionHandler(NotSignInBrowserException.class)
  public ResponseDto notSignInUserException(NotSignInBrowserException e) {

    return fail("Unauthorized", e.getMessage());

  }

  /**
   * .
   */

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseDto methodArgumentNotValidException(MethodArgumentNotValidException e) {

    return fail(makeCode(e), makeMessage(e));

  }


  /**
   * .
   */

  private ResponseDto fail(String code, String message) {

    return new ResponseDto(false, null, "에러 발생", Error.from(code, message));

  }

  private String makeCode(MethodArgumentNotValidException e) {

    return e.getBindingResult().getFieldError().getCode();

  }

  private String makeMessage(MethodArgumentNotValidException e) {

    return e.getBindingResult().getFieldError().getDefaultMessage();

  }
}
