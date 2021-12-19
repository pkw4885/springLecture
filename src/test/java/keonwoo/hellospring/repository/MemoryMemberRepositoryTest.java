package keonwoo.hellospring.repository;

import keonwoo.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;


class MemoryMemberRepositoryTest {

    MemoryMemberRepository respository = new MemoryMemberRepository();

    @AfterEach // 테스트 한 번 할 때 마다, clearstore 함
    public void afterEach() {

        respository.clearStore();

    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        respository.save(member);

        Member result = respository.findById(member.getId()).get();
//        Assertions.assertEquals(member, result);
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {

        Member member1 = new Member();
        member1.setName("spring1");
        respository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        respository.save(member2);

        Member result = respository.findByName("spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member3 = new Member();
        member3.setName("spring3");
        respository.save(member3);

        Member member4 = new Member();
        member4.setName("spring4");
        respository.save(member4);

        List<Member> result = respository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);

    }



}
