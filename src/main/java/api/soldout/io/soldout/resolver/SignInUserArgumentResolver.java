package api.soldout.io.soldout.resolver;

import api.soldout.io.soldout.annotation.SignInUser;
import api.soldout.io.soldout.mapper.UserMapper;
import api.soldout.io.soldout.service.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * .
 */

@Component
@RequiredArgsConstructor
public class SignInUserArgumentResolver implements HandlerMethodArgumentResolver {

  private final SecurityService securityService;

  private final UserMapper userMapper;

  /**
   * .
   */

  @Override
  public boolean supportsParameter(MethodParameter parameter) {

    return parameter.hasParameterAnnotation(SignInUser.class);

  }

  @Override
  public Object resolveArgument(MethodParameter parameter,
      ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest,
      WebDataBinderFactory binderFactory) {

    int userId = securityService.getCurrentUserId();

    return userMapper.findById(userId);

  }

}
