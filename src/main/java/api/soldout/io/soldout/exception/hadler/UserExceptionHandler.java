package api.soldout.io.soldout.exception.hadler;

import api.soldout.io.soldout.controller.user.UserController;
import api.soldout.io.soldout.dtos.user.response.ResponseDTO;
import api.soldout.io.soldout.exception.AlreadyExistEmailException;
import api.soldout.io.soldout.exception.AlreadySignInUserException;
import api.soldout.io.soldout.exception.NotExistSignInUserException;
import api.soldout.io.soldout.exception.NotExistUserException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = UserController.class)
public class UserExceptionHandler {

  @ExceptionHandler(AlreadyExistEmailException.class)
  public ResponseDTO sameEmailException(AlreadyExistEmailException e){
    return ResponseDTO.fail("", e.getMessage());
  }

  @ExceptionHandler(NotExistUserException.class)
  public ResponseDTO noUserException(NotExistUserException e){
    return ResponseDTO.fail("", e.getMessage());
  }

  @ExceptionHandler(AlreadySignInUserException.class)
  public ResponseDTO noUserException(AlreadySignInUserException e){
    return ResponseDTO.fail("", e.getMessage());
  }

  @ExceptionHandler(NotExistSignInUserException.class)
  public ResponseDTO noUserException(NotExistSignInUserException e){
    return ResponseDTO.fail("", e.getMessage());
  }
}
