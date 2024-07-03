package com.openbank.web;

import com.openbank.OpenBankingClient;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    public static final String USER_SEQ_NO = "userSeqNo";
    OpenBankingClient openBankingClient;

    public PageController(OpenBankingClient openBankingClient) {
        this.openBankingClient = openBankingClient;
    }

    @GetMapping()
    public String login() {
        return "auth";
    }

    @GetMapping("/home")
    public String home(@RequestParam("accessToken") String accessToken,
                       @RequestParam(USER_SEQ_NO) String userSeqNo,
                       Model model) {
        model.addAttribute("accessToken", accessToken);
        model.addAttribute(USER_SEQ_NO, userSeqNo);
        return "home";
    }

    @GetMapping("/main")
    public String mainPage(@RequestParam("name") String name, HttpServletRequest httpServletRequest,
                           Model model) {
        model.addAttribute("name", name);
        return "main";
    }
}
