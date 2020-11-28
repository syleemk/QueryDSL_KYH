package study.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.querydsl.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //메서드 이름 쿼리 생성 (select m from Member m where username = :username)
    List<Member> findByUsername(String username);
}
