package api.soldout.io.soldout.service.security;

public interface SecurityService {
  String createToken(String subject, long ttlMillis);

  String getSubject(String token);
}
