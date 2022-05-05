package api.soldout.io.soldout.member.repository;

import api.soldout.io.soldout.member.domain.Member;
import api.soldout.io.soldout.member.dto.CreateMemberRequestDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class MemberDb implements MemberRepo {

    private HashMap<Integer, Member> db = new HashMap<>();
    private static int id = 0;

    @Override
    public boolean create(CreateMemberRequestDto CreateMemberRequestDto) {
        try {
            db.put(id,CreateMemberRequestDto);
            id++;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Member findByEmail(String email) {
        try {
            return db.get(email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
