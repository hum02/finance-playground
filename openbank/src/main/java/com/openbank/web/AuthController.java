package com.openbank.web;

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

    public static final String BEARER = "Bearer ";
    private OAuthClient oAuthClient;

    public AuthController(OAuthClient oAuthClient) {
        this.oAuthClient = oAuthClient;
    }

    @GetMapping("/auth")
    public String getAuth() {
        return "redirect:" + oAuthClient.getAuthUri();
    }

//    @GetMapping("/authorize")
//    public String getAuthorizationCode(@RequestParam("code") String code, RedirectAttributes redirectAttributes) {
//        String accessToken = oAuthClient.getAccessToken(code);
//        redirectAttributes.addAttribute("accessToken", accessToken);
//        return "redirect:" + "/";
//    }

    @GetMapping("/openbank")
    public String getAuthorizationCode2(@RequestParam("code") String code, RedirectAttributes redirectAttributes) {
        TokenResponse response = oAuthClient.getAccessToken(code);
        redirectAttributes.addAttribute("accessToken", response.accessToken());
        return "redirect:" + "/home";
    }

    @PostMapping("/login")
    public void login(@RequestParam("name") String name, @RequestParam("accessToken") String accessToken,
                      HttpServletResponse response) throws IOException {
        Cookie jwtCookie = new Cookie("Authorization", BEARER + accessToken);

        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(1000 * 60 * 60);
        response.addCookie(jwtCookie);

        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8.toString());
        response.sendRedirect("/main?name=" + encodedName);
    }
}
