package keonwoo.hellospring.service;

import keonwoo.hellospring.domain.Member;
import keonwoo.hellospring.repository.MemberRepository;
import keonwoo.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    //서비스 딴에서는 좀 더 비즈니스 처리를 하는 게 원칙이기 때문에, 네이밍을 그에 맞게 지음

    // 이건 멤버 리포지토리를 만들때마다 다른 리포지토리를 쓰게 됨 -> private final MemberRepository memberRepository = new MemoryMemberRepository() ;

    private final MemberRepository memberRepository;

    // 외부에서 넣어주도록 함
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    ///## 회원가입 ##///

    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X

        //ver1
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m ->{
//            throw new IllegalStateException("이미 존재하는 회원입니다");
//        } );

        //ver2
//        memberRepository.findByName(member.getName())
//                .ifPresent(d -> {
//                    throw new IllegalStateException("이미 존재하는 회원입니다");
//                });

        //ver3 - 위 ver2를 메서드로 뽑아 놓는 게 좋음
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) //리포지토리에서 이름을 찾아라 ! 찾는 이름은 member.getName()
                        .ifPresent(d -> { //만약 null이 아니라면 (존재한다면)
                            throw new IllegalStateException("이미 존재하는 회원입니다"); //
                        });
    }

    ///## 전체 회원 조회 ##///

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
