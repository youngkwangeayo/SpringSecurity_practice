package com.example.securitiesbasic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.securitiesbasic.config.oauth.PrincipalOauth2UserService;

// 구글로그인하면 코드받음(인증) 2 엑세스토큰(권한)
//3 토큰을 이용해서 유저 프로필가져오고 그 정보로 회원가입시킴

// @EnableWebSecurity 스프링 시큐리티 필터가 스프링 체인에 등록됨 필터는 바로 요 컨피그파일 이였지만 버전 2로 올라가면서 빈생성은 메소드로
@Configuration
public class SecurityConfig {

    @Autowired
    PrincipalOauth2UserService userService;

    @Bean
    BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeRequests()
            .antMatchers("/user/**").authenticated() //유저 인증필요
            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
            .anyRequest().permitAll()//위설정빼고는 가능해요
            .and()
            .formLogin() //로그인방식
            .loginPage("/login")//로그인 페이지를 지정해준다 시큐리티에서는 시큐리티기본로그인폼이있기에 지정해줘야 가로채감
            // .usernameParameter("email") 데이탈리스 로드바이유저네임에 매게변수 작동을 위해 바꿔주는메소드
            .loginProcessingUrl("/loginProc") // 로그인 요청을 낚아채서 시큐리티세션을 넣어줌
            .defaultSuccessUrl("/")//완료되면 가질경로 하지만 요청유알엘이 있을때 인증 하면 요청유알엘로가짐
            .and()
            .oauth2Login()//oauth2 로그인 인데 oatu2_clint 라이브러리로 로그인할시 명시
            .loginPage("/login")//여기까지가 로그인까지 ok
            .userInfoEndpoint() 
            .userService(userService);
            
            // .and()
			// 	.oauth2Login()
			// 	.loginPage("/login")
			// 	.userInfoEndpoint()
			// 	.userService(principalOauth2UserService);
            

        return http.build();
    }
    
}
