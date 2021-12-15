package keonwoo.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model hello) {
        hello.addAttribute("data", "hello!");
        hello.addAttribute("data2", "hello3 !!");
        return "hello"; // url은 hello이고, resources - 템플릿에 있는 hello.html 에 이 메서드를 보낸다. hello.html은?
    }

    @GetMapping("hello2") // 수업엔 없는 내용, url을 바꾸고 실행하여 GetMapping과 return 에 대한 이해를 높혀봤다
    public String hello2(Model hello2) {
        hello2.addAttribute("data3", "hello-b!");
        hello2.addAttribute("data4", "hello-bbb!!");
        return "hello"; // url은 hello2이고, resources - 템플릿에 있는 hello.html 에 이 메서드를 보낸다. hello.html은?
    }

    @GetMapping("hello-mvc")
    public String helloMvc() {

    }

}
