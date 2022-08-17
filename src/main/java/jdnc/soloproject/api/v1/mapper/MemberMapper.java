package jdnc.soloproject.api.v1.mapper;

import jdnc.soloproject.api.v1.Member;
import jdnc.soloproject.api.v1.dto.MemberPostDto;
import jdnc.soloproject.api.v1.dto.MemberResponseDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    MemberResponseDto memberToMemberResponseDto(Member member);
}
/*
public class MemberMapper {
    public Member memberPostDtoToMember(MemberPostDto memberPostDto){
        return new Member(0L,
                memberPostDto.getEmail(),
                memberPostDto.getName(),
                memberPostDto.getPhone());
    }

    public MemberResponseDto memberToMemberResponseDto (Member member){
        return new MemberResponseDto(member.getMemberId(),
                member.getEmail(),
                member.getName(),
                member.getPhone());
    }
}*/
