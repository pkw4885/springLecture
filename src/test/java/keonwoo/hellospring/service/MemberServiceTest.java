package keonwoo.hellospring.service;

import keonwoo.hellospring.domain.Member;
import keonwoo.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //테스트는 한글로도 많이 작성합

    // MemberService memberService = new MemberService();
    // MemoryMemberRepository memberRepository = new MemoryMemberRepository(); // MemberServive.java에 있는 객체와 다른 객체여서 다른 리포지토리를 사용중

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); // Memberservice 입장에서 디펜던시 이젝션 DI 라고 한다
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given - 뭔가 주어졌는데
        Member member = new Member();
        member.setName("parkkeonwoo");

        //when - 뭐를 실행했는데(스펙)
        Long saveId = memberService.join(member); // 회원가입 완료~
//        System.out.println(saveId);
//        System.out.println();

        //then - 결과가 도출됨
        Member findMember = memberService.findOne(saveId).get();  // 회원가입 조회해서 findMember에에 저장
//        System.out.println(findMember);
//        System.out.println(findMember.getName());
//        System.out.println(member.getName());
        if (findMember.getName() == member.getName()) {
            System.out.println("오류 없음");
        }
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName()); // member에 저장된 것과 findMember에 저장된 것이 같냐

    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("pkw");

        Member member2 = new Member();
        member2.setName("pkw");

        //when
            //try-catch 문을 이용해서 하기
//            memberService.join(member1);
//            try {
//                memberService.join(member2);
//                fail();
//            } catch (IllegalStateException e) {
//                Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//            }

            //
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 콤마 뒤의 로직을 실행했을 때 저 exception 클래스가 터져야함

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다"); // 덤으로 메시지도 검증


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}