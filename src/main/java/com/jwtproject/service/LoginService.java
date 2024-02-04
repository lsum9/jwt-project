package com.jwtproject.service;

import com.jwtproject.dto.UserDto;
import com.jwtproject.mapper.LoginMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginMapper loginMapper;
    private final PasswordEncoder passwordEncoder;


    public boolean idCheck(UserDto userDto){
        if(loginMapper.idCheck(userDto) == null){
            return false;
        }else {
            return true;
        }
    }

    //로그인
    public UserDto signIn(UserDto userDto){
        //아이디로 회원 조회
        boolean idCheck = idCheck(userDto);
        if(idCheck){
            //아이디 존재하면 비번일치여부 확인
            String pwdById =  loginMapper.pwdById(userDto);

            if(passwordEncoder.matches(userDto.getUserPwd(), pwdById)){
                return userDto;
            }else{
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }//비번일치확인 if end
        }else{
            throw new IllegalArgumentException("아이디가 일치하지 않습니다.");
        }//아이디확인 if end
    }//sign end

    //회원가입
    public int signUp(UserDto userDto){

        boolean idCheck = idCheck(userDto);
        if(idCheck){
            //아이디 존재하면 중복아이디 알림
            throw new IllegalArgumentException("중복된 아이디입니다.");
        }else{
            //->해당아이디 없으면 가입진행
            String encodePwd = passwordEncoder.encode(userDto.getUserPwd());
            userDto.setUserPwd(encodePwd);
            return loginMapper.signUp(userDto);
        }//아이디확인 if end
        //아이디 중복 안되면 나머지 정보 추가 인서트하여 가입

    }
}
