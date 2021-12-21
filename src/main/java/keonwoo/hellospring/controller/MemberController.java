package keonwoo.hellospring.controller;

import keonwoo.hellospring.domain.Member;
import keonwoo.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    //  Controller
    //   private final MemberService memberService = new MemberService();   <<<< 좌측과 같이 하면, 별 기능이 없는데 할 때 마다 불러야된다? 무슨말이지? 일단 ㅇㅋ

    private final MemberService memberService;

    @Autowired // 스프링에 있는 변수를 연결시켜줌? 스프링빈에 있던 mem , 의존관계 주입
    public MemberController(MemberService memberService) { //MemberService는 순수한 java 클래스라서 글로 이동한다음 !
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        System.out.println("member = " + member.getName());

        return "redirect:/";

    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
