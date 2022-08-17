package jdnc.soloproject.api.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private Long memberId;
    private String name;
    private String email;
    private String phone;


}
