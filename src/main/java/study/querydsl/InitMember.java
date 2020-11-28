package study.querydsl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.Team;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//스프링 애플리케이션이 실행되면서 이게 bean등록되면 postconstruct 수행됨
@Profile("local") //profile이 local일때만 동작
@Component // 스프링 빈 등록, component 스캔에 의해 해당 객체를 스프링 컨테이너가 관리하도록 함
@RequiredArgsConstructor
public class InitMember {

    private final InitMemberService initMemberService;
    
    //spring 라이프 사이클 동작하는 방식 때문에
    //transactional과 postconstruct 같이 할 수 없음, 분리해줘야함
    @PostConstruct
    public void init() {
        initMemberService.init();
    }
    
    @Component
    static class InitMemberService {
        @PersistenceContext
        EntityManager em;

        @Transactional
        public void init() {
            Team teamA = new Team("teamA");
            Team teamB = new Team("teamB");
            em.persist(teamA);
            em.persist(teamB);

            for (int i = 0; i < 100; i++) {
                Team selectedTeam = i % 2 == 0 ? teamA : teamB;
                em.persist(new Member("member"+i, i, selectedTeam));
            }
        }
    }
}
