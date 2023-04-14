package com.example.securitiesbasic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.securitiesbasic.config.auth.PrincipalDetails;
import com.example.securitiesbasic.entity.User;
import com.example.securitiesbasic.mapper.UserMapper;

@Controller
public class MainController {
    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public String mainHome() {
        
       
        return "home";
    }
    
    @GetMapping("/login")
    public String login() {
        
        
        return "loginForm";
    }
    //비밀번호 엔코딩한거랑 로그인 비교를 시큐리티가 하는데 이때 엔코딩되어있지않는걸로 로그인 요청을해도안됨
    @PostMapping("/joinProc")
    public String joinProc(User user) {
        
        String endCodingPass = user.getPassword();
       
        user.setPassword(encoder.encode(endCodingPass));
        userMapper.join(user);
        
        
        return "loginForm";
    }

    @GetMapping("/join")
    public String join() {
        
        
        return "joinForm";
    }

    



    @GetMapping("/user")
    
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails user,Model model) {
        
        
        return user.getUsername();
    }
    
    @GetMapping("/admin")
    
    public @ResponseBody String  admin() {
        
        
        return "admin페이지";
    }
    
}
