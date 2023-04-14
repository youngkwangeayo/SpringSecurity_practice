package com.example.securitiesbasic.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.securitiesbasic.entity.User;
import com.example.securitiesbasic.mapper.UserMapper;


//시큐리티 설정에서 로그인 프로세싱유알엘 걸어둔데 그 유알엘 요청이 들어오면 밑에 로드바이유저네임 메소드가 바로실행된다
//자동으로 UserDetailsService ioc 되어있는 함수실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    //매개변수에 스트링유저네임은 시큐리티필터체인에 설정한 로그인 페이지 경로에 input name 값이다. 
    // 설정을 변경해서 사용할수있다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("시큐리티 로드바이 유저네임실행");
        User user = userMapper.findByUserName(username);
        if(user!=null){
            //리턴이 시큐리티session
            System.out.println("시큐리티세션 리턴되는데");
            return new PrincipalDetails(user);
        }
        return null;
    }
    
}
