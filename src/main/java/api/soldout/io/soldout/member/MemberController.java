package api.soldout.io.soldout.member;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @PostMapping("/members")
    @ResponseStatus(HttpStatus.CREATED)
    public <CreatMemberRequestDto> void join(@RequestBody final CreatMemberRequestDto creatMemberRequestDto)
}
