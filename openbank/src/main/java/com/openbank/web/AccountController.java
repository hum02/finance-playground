package com.openbank.web;

import com.openbank.OpenBankingClient;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.util.Arrays.stream;

@Controller
@RequestMapping("/account")
public class AccountController {

    public static final String AUTHORIZATION = "Authorization";
    public static final String USER_SEQ_NO = "userSeqNo";
    private final OpenBankingClient openBankingClient;

    public AccountController(OpenBankingClient openBankingClient) {
        this.openBankingClient = openBankingClient;
    }

    @GetMapping
    public String getAccountList(HttpServletRequest httpServletRequest, Model model) {
        Cookie accessTokenCookie = getCookie(AUTHORIZATION, httpServletRequest);
        Cookie userSeqNoCookie = getCookie(USER_SEQ_NO, httpServletRequest);

        UserInfoResponse userInformation = openBankingClient.getUserInformation(
                accessTokenCookie.getValue(), userSeqNoCookie.getValue()
        );

        model.addAttribute("accountList", userInformation.res_list());
        return "accounts";
    }
    
    private static Cookie getCookie(String name, HttpServletRequest httpServletRequest) {
        return stream(httpServletRequest.getCookies())
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(name + "쿠키가 존재하지 않습니다."));
    }
}
