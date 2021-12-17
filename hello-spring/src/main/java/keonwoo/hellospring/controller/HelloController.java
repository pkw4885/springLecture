package keonwoo.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String helloMvc(@RequestParam("name2") String name2, Model model) {
        model.addAttribute("name3", name2);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // 이 방식은 html에 내려주는 것이 아니라, 이 정보 자체를 그냥 view로 띄워버림
    public String helloString(@RequestParam("param") String name) {
        return "hello" + name; //
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi (@RequestParam("param") String names) {
        Hello hello = new Hello(); // Class Hello 생성자
        hello.setName(names);
        return hello; // 객체가 오면 json 방식으로 만들어서 보내겠다 (기본정책)
    }
    static class Hello {
        private String name;
        private String id; //수업에 없는 내용 정보 입력의 흐름의 이해를 돕기 위해 넣어봄

        public String getName() { // getPame 이라고 하니까 pame : (value) 로 json 형식으로 작성됨 !
            return name + " " + id;
        }

        public void setName(String name) {
            this.name = name;
            this.id = name; //수업에 없는 내용 정보 입력의 흐름의 이해를 돕기 위해 넣어봄
        }
    }

}
