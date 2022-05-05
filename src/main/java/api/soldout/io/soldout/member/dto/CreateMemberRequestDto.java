package api.soldout.io.soldout.member.dto;

import api.soldout.io.soldout.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateMemberRequestDto extends Member {

    private String email;
    private String password;
    private String name;

    public Member create() {
        Member member = Member.builder()
                .email(this.email)
                .name(this.name)
                .password(this.password)
                .build();
        return member;
    }
}
