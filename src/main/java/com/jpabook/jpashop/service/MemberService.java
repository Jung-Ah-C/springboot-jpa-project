package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// 모든 데이터 변경이나 로직들은 가급적 트랜잭션 안에서 실행되어야 함
@Transactional(readOnly = true) // 조회만 하는 경우에 readOnly 옵션을 true로 주면 jpa 성능 최적화가 됨, 클래스 안에 조회 기능이 많을 경우에는 전체적으로 readOnly 옵션을 추가
@RequiredArgsConstructor // final 필드가 있는 것에 대해서만 생성자를 만들어주는 어노테이션
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional(readOnly = false) // 읽기가 아닌 경우엔 readOnly 옵션을 false 설정
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName()); // 멀티 쓰레드로 실행할 경우 동시에 중복 검증 메소드가 실행될 수도 있어서, 이럴 경우에는 name을 unique로 잡아줌
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 개별 조회
    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
