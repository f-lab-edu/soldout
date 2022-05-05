package api.soldout.io.soldout.member.domain;

import lombok.Builder;

@Builder
public class Member {

    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
}
