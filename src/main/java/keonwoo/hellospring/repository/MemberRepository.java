package keonwoo.hellospring.repository;

import keonwoo.hellospring.controller.HelloController;
import keonwoo.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원이 저장소에 저장됨

    Optional<Member> findById(Long id);

    Optional<Member> findByName(String name);

    List<Member> findAll(); // 모든 회원 리스트 반환함

}
