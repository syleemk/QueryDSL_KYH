package study.querydsl.dto;

import lombok.Data;

@Data
public class MemberSearchCondition {//검색조건 request dto
    //회원명, 팀명, 나이(ageGoe, ageLoe)

    private String username;
    private String teamName;
    private Integer ageGoe; //Integer wrapper 객체 쓰는이유는, 값이 null일 수도 있어서
    private Integer ageLoe;
}
