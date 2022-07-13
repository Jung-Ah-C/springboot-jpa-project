package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // 컴포넌트 스캔에 의해서 자동으로 스프링 빈에 등록되어서 관리됨
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    // 회원 등록
    public void save(Member member) {
        em.persist(member);
    }

    // 회원 id로 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // 전체 회원 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // 첫번째 : JPQL 작성, 두번째 : 반환 타입 명시
                .getResultList(); // 조회한 결과를 List로 만들어줌
    }

    // 회원 이름으로 조회
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
