package api.soldout.io.soldout.member.repository;

import api.soldout.io.soldout.member.domain.Member;
import api.soldout.io.soldout.member.dto.CreateMemberRequestDto;

public interface MemberRepo {

    boolean create(CreateMemberRequestDto CreateMemberRequestDto);

    Member findByEmail(String email);
}
