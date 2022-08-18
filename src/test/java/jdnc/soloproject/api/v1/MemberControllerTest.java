package jdnc.soloproject.api.v1;

import com.google.gson.Gson;
import jdnc.soloproject.api.v1.dto.MemberPostDto;
import jdnc.soloproject.api.v1.dto.MemberResponseDto;
import jdnc.soloproject.api.v1.mapper.MemberMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper mapper;

    @Test
    void postMember()throws Exception {
    //given
        MemberPostDto post = new MemberPostDto("hgd@gmail.com",
                "홍길동",
                "010-1234-5678");

        MemberResponseDto response = new MemberResponseDto(1L,"hgd@gmail.com",
                "홍길동",
                "010-1234-5678");

        given(mapper.memberPostDtoToMember(Mockito.any(MemberPostDto.class))).willReturn(new Member());
        given(memberService.createMember(Mockito.any(Member.class))).willReturn(new Member());
        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class))).willReturn(response);

        String content = gson.toJson(post); // 직렬화 java -> json
    //when - 요청 검증
        ResultActions actions =
                mockMvc.perform(
                        post("/v1/members") // post 매서드 실행
                                .accept(MediaType.APPLICATION_JSON) //클라이언트에서 받을 응답데이터 타입
                                .contentType(MediaType.APPLICATION_JSON) // 서버에서 받을 요청데이터 타입(request body 타입)
                                .content(content) // request body 데이터
                        );
    //then - 응답 검증
        MvcResult result = actions
                .andExpect(status().isCreated()) // (8)
                .andExpect(jsonPath("$.data.email").value(post.getEmail()))
                .andExpect(jsonPath("$.data.name").value(post.getName()))
                .andExpect(jsonPath("$.data.phone").value(post.getPhone()))
                .andReturn();

    }

    @Test
    void getMember() {
    }

    @Test
    void getMembers() {
    }
}