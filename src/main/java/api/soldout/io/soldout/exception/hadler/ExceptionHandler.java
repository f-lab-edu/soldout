package api.soldout.io.soldout.exception.hadler;

import api.soldout.io.soldout.controller.product.ProductController;
import api.soldout.io.soldout.controller.user.UserController;
import api.soldout.io.soldout.dtos.response.ResponseDto;
import api.soldout.io.soldout.dtos.response.ResponseDto.Error;
import api.soldout.io.soldout.exception.AlreadyExistEmailException;
import api.soldout.io.soldout.exception.AlreadySignInBrowserException;
import api.soldout.io.soldout.exception.NotSignInBrowserException;
import api.soldout.io.soldout.exception.NotValidEmailException;
import api.soldout.io.soldout.exception.NotValidPasswordException;
import api.soldout.io.soldout.exception.NotValidTokenException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *.
 */

@RestControllerAdvice(basePackageClasses = {UserController.class, ProductController.class})
public class ExceptionHandler {

  /**
   * 요청 객체의 유효성 검사에 대한 예외 처리를 위한 예외 처리.
   */

  @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseDto methodArgumentNotValidException(MethodArgumentNotValidException e) {

    return fail(makeCode(e), makeMessage(e));

  }

  /**
   * .
   */

  @org.springframework.web.bind.annotation.ExceptionHandler(AlreadyExistEmailException.class)
  public ResponseDto alreadyExistEmailException(AlreadyExistEmailException e) {

    return fail("Unauthorized", e.getMessage());

  }

  /**
   * .
   */

  @org.springframework.web.bind.annotation.ExceptionHandler(NotValidEmailException.class)
  public ResponseDto notValidEmailException(NotValidEmailException e) {

    return fail("Unauthorized", e.getMessage());

  }

  /**
   * .
   */

  @org.springframework.web.bind.annotation.ExceptionHandler(NotValidPasswordException.class)
  public ResponseDto notValidPasswordException(NotValidPasswordException e) {

    return fail("Unauthorized", e.getMessage());

  }

  /**
   * .
   */

  @org.springframework.web.bind.annotation.ExceptionHandler(AlreadySignInBrowserException.class)
  public ResponseDto alreadySignInUserException(AlreadySignInBrowserException e) {

    return fail("Unauthorized", e.getMessage());

  }

  /**
   * .
   */

  @org.springframework.web.bind.annotation.ExceptionHandler(NotSignInBrowserException.class)
  public ResponseDto notSignInUserException(NotSignInBrowserException e) {

    return fail("Unauthorized", e.getMessage());

  }

  /**
   * .
   */

  @org.springframework.web.bind.annotation.ExceptionHandler(NotValidTokenException.class)
  public ResponseDto notValidTokenException(NotValidTokenException e) {

    return fail("Unauthorized", e.getMessage());

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
