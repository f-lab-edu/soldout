package api.soldout.io.soldout.exception.hadler;

import api.soldout.io.soldout.controller.user.UserController;
import api.soldout.io.soldout.dtos.user.response.ResponseDTO;
import api.soldout.io.soldout.dtos.user.response.ResponseDTO.Error;
import api.soldout.io.soldout.exception.AlreadyExistEmailException;
import api.soldout.io.soldout.exception.AlreadySignInUserException;
import api.soldout.io.soldout.exception.NotSignInUserException;
import api.soldout.io.soldout.exception.NotValidEmailException;
import api.soldout.io.soldout.exception.NotValidPasswordException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = UserController.class)
public class UserExceptionHandler {

  @ExceptionHandler(AlreadyExistEmailException.class)
  public ResponseDTO sameEmailException(AlreadyExistEmailException e){
    return fail("", e.getMessage());
  }

  @ExceptionHandler(NotValidPasswordException.class)
  public ResponseDTO notValidPasswordException (NotValidPasswordException e) {
    return fail("", e.getMessage());
  }

  @ExceptionHandler(NotValidEmailException.class)
  public ResponseDTO notValidEmailException(NotValidEmailException e){
    return fail("", e.getMessage());
  }

  @ExceptionHandler(AlreadySignInUserException.class)
  public ResponseDTO noUserException(AlreadySignInUserException e){
    return fail("", e.getMessage());
  }

  @ExceptionHandler(NotSignInUserException.class)
  public ResponseDTO noUserException(NotSignInUserException e){
    return fail("", e.getMessage());
  }

  private ResponseDTO fail(String code, String message) {
    return new ResponseDTO(false, null, "", Error.from(code, message));
  }
}
