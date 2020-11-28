package study.querydsl.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Team {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    // new 해서 리스트 객체 넣어주는 것은 그냥 관례
    // null 포인터 exception같은 거 피할려고 
    // 메모리도 얼마 안먹음 어차피
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
