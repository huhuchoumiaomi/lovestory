package com.ourlovestory.lovestory.controller;

import com.ourlovestory.lovestory.dto.AccessTokenDTO;
import com.ourlovestory.lovestory.dto.GithubUser;
import com.ourlovestory.lovestory.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("112be94d474c1486bec1");
        accessTokenDTO.setClient_secret("bcd3c9e841137e934abe2f2b60799f2b3f2a9107");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:9999/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
