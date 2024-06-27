package com.openbank.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AuthController {

    private OAuthClient oAuthClient;

    public AuthController(OAuthClient oAuthClient) {
        this.oAuthClient = oAuthClient;
    }

    @GetMapping("/auth")
    public String getAuth() {
        return "redirect:"+oAuthClient.getAuthUri();
    }

    @ResponseBody
    @GetMapping("/openbank")
    public String getAuthorizationCode(@RequestParam("code") String code){
        String accessToken = oAuthClient.getAccessToken(code);
        return accessToken;
    }
}
