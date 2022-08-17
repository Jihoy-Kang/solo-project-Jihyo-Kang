package jdnc.soloproject.api.v1;

import jdnc.soloproject.api.v1.dto.MemberPostDto;
import jdnc.soloproject.api.v1.dto.MemberResponseDto;
import jdnc.soloproject.api.v1.mapper.MemberMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity  postMember(@RequestBody MemberPostDto memberPostDto) {

        Member member = mapper.memberPostDtoToMember(memberPostDto);

        // (3)
        Member response = memberService.createMember(member);

        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response), HttpStatus.CREATED);
    }

        @GetMapping("/{member-id}")
        public ResponseEntity getMember(@PathVariable("member-id")long memberId) {

            Member response = memberService.findMember(memberId);

            return new ResponseEntity<>(mapper.memberToMemberResponseDto(response),HttpStatus.OK);
        }

        @GetMapping
        public ResponseEntity getMembers() {

            List<Member> members = memberService.findMembers();

            List<MemberResponseDto> response =
                    members.stream()
                            .map(member -> mapper.memberToMemberResponseDto(member))
                            .collect(Collectors.toList());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
}
