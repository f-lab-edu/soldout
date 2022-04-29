package api.soldout.io.soldout.service;

public interface SecurityService {
  String createToken(String subject, long ttlMillis);

  String getSubject(String token);
}
