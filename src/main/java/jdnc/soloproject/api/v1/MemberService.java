package jdnc.soloproject.api.v1;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    public Member createMember(Member member) {

        Member cratedMember = member;
        return cratedMember;
    }

    public Member findMember(long memberId) {
        Member member =
                new Member(memberId, "hgd@gmail.com", "홍길동", "010-1234-5678");
        return member;
    }

    public List<Member> findMembers() {
        List<Member> members = List.of(
                new Member(1L, "hgd@gmail.com", "홍길동", "010-1234-5678"),
                new Member(2L, "lml@gmail.com", "이몽룡", "010-1111-2222")
        );
        return members;
    }


}
