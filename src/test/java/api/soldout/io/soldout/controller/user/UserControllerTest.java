package api.soldout.io.soldout.controller.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import api.soldout.io.soldout.controller.request.SignInRequest;
import api.soldout.io.soldout.controller.request.SignUpRequest;
import api.soldout.io.soldout.controller.response.ResponseDto;
import api.soldout.io.soldout.domain.UserDto;
import api.soldout.io.soldout.interceptor.SessionSignInHandlerInterceptor;
import api.soldout.io.soldout.resolver.SignInUserArgumentResolver;
import api.soldout.io.soldout.service.security.SecurityService;
import api.soldout.io.soldout.service.user.UserService;
import api.soldout.io.soldout.service.user.command.SignUpCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(UserController.class)
class UserControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  UserService userService;

  @MockBean
  SecurityService securityService;

  @MockBean
  SessionSignInHandlerInterceptor interceptor;

  @MockBean
  SignInUserArgumentResolver resolver;

  ObjectMapper objectMapper;

  int userId = 1;
  String email = "email";
  String password = "password";
  String name = "name";
  String phone = "010-0000-0000";
  String address = "address";

  @BeforeEach
  void init() throws Exception {

    when(interceptor.preHandle(any(), any(), any()))
        .thenReturn(true);

    objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

  }

  @Test
  @DisplayName("회원가입 로직 테스트")
  void signUp() throws Exception {
    // given
    ArgumentCaptor<SignUpCommand> captor = ArgumentCaptor.forClass(SignUpCommand.class);

    SignUpRequest request = new SignUpRequest(
        email, password, name, phone, address
    );

    ResponseDto response =  new ResponseDto(
        true, null, "회원가입 성공", null
    );

    // when
    ResultActions result = mockMvc.perform(post("/user/signup")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)));

    // then
    result.andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(response)))
        .andDo(print());

    verify(userService, times(1)).signUp(captor.capture());

    SignUpCommand command = captor.getValue();

    assertThat(command.getEmail()).isEqualTo(request.getEmail());
    assertThat(command.getPassword()).isEqualTo(request.getPassword());
    assertThat(command.getName()).isEqualTo(request.getName());
    assertThat(command.getPhone()).isEqualTo(request.getPhone());
    assertThat(command.getAddress()).isEqualTo(request.getAddress());

  }

  @Test
  @DisplayName("이메일 검증 로직 테스트 : 이미 존재하는 경우")
  void checkEmailSuccessTest() throws Exception {
    // given
    ResponseDto response = new ResponseDto(
        true, null, "이미 존재하는 이메일", null
    );

    // when
    when(userService.isExistEmail(email)).thenReturn(true);

    // then
    ResultActions result = mockMvc.perform(get("/user/email/exists")
        .param("email", email));

    result.andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(response)))
        .andDo(print());
  }

  @Test
  @DisplayName("이메일 검증 로직 테스트 : 존재하는 이메일이 없는 경우")
  void checkEmailFailTest() throws Exception {
    // given
    ResponseDto response = new ResponseDto(
        true, null, "사용 가능한 이메일", null
    );

    // when
    when(userService.isExistEmail(email)).thenReturn(false);

    // then
    ResultActions result = mockMvc.perform(get("/user/email/exists")
        .param("email", email));

    result.andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(response)))
        .andDo(print());
  }

  @Test
  @DisplayName("로그인 로직 검증 테스트")
  void signInTest() throws Exception {
    // given
    SignInRequest request = new SignInRequest(email, password);

    UserDto user = UserDto.builder()
        .id(1)
        .email(request.getEmail())
        .password(request.getPassword())
        .build();

    ResponseDto response = new ResponseDto(
        true, null, "로그인 성공", null
    );

    // when
    when(userService.checkEmailAndPw(request.getEmail(), request.getPassword()))
        .thenReturn(user);

    ResultActions result = mockMvc.perform(post("/user/signin")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)));

    // then
    result.andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(response)))
        .andDo(print());

    verify(securityService, times(1)).signIn(user.getId());

  }

  @Test
  @DisplayName("로그아웃 로직 검증")
  void logOutTest() throws Exception {
    // given
    ResponseDto response = new ResponseDto(
        true, null, "로그아웃 성공", null
    );

    // when
    ResultActions result = mockMvc.perform(post("/user/logout"));

    // then
    result.andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(response)));

    verify(securityService, times(1)).logOut();

  }

  @Test
  @DisplayName("로그인 검증 로직 테스트")
  void signInCheckTest() throws Exception {
    ResponseDto response = new ResponseDto(
        true, null, "로그인 체크", null
    );

    when(resolver.resolveArgument(any(), any(), any(), any()))
        .thenReturn(userId);

    ResultActions result = mockMvc.perform(get("/user/signin/check")
        .param("userId", String.valueOf(1)));

    result.andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(response)));

  }
}