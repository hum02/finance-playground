package com.openbank.web;

import com.openbank.OAuthClient;
import com.openbank.web.dto.TokenResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class AuthController {

    private OAuthClient oAuthClient;

    public AuthController(OAuthClient oAuthClient) {
        this.oAuthClient = oAuthClient;
    }

    @GetMapping("/auth")
    public String getAuth() {
        return "redirect:" + oAuthClient.getAuthUri();
    }

    @GetMapping("/authorize")
    public String getAuthorizationCode2(@RequestParam("code") String code, RedirectAttributes redirectAttributes) {
        TokenResponse response = oAuthClient.getAccessToken(code);

        redirectAttributes.addAttribute("accessToken", response.accessToken());
        redirectAttributes.addAttribute("userSeqNo", response.userSeqNo());
        return "redirect:" + "/home";
    }

    @PostMapping("/login")
    public void login(@RequestParam("name") String name, @RequestParam("accessToken") String accessToken,
                      @RequestParam("userSeqNo") String userSeqNo, HttpServletResponse response) throws IOException {
        setCookie("Authorization", accessToken, response);
        setCookie("userSeqNo", userSeqNo, response);

        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8.toString());
        response.sendRedirect("/main?name=" + encodedName);
    }

    private static void setCookie(String headerName, String accessToken, HttpServletResponse response) {
        Cookie jwtCookie = new Cookie(headerName, accessToken);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(1000 * 60 * 60);
        response.addCookie(jwtCookie);
    }
}
