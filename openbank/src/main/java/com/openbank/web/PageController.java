package com.openbank.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @GetMapping()
    public String login() {
        return "auth";
    }

    @GetMapping("/home")
    public String home(@RequestParam("accessToken") String accessToken,
                       Model model) {
        // 모델에 정보 추가
        model.addAttribute("accessToken", accessToken);

        return "home";
    }

    @GetMapping("/main")
    public String mainPage(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);

        // 메인 페이지 뷰 반환
        return "main";
    }
}
