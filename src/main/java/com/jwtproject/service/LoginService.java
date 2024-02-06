package com.jwtproject.service;

import com.jwtproject.dto.UserDto;
import com.jwtproject.mapper.LoginMapper;
import com.jwtproject.mapper.RefreshTokenMapper;
import com.jwtproject.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginMapper loginMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenMapper refreshTokenMapper;


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
            String pwdById =  loginMapper.pwdById(userDto);//저장된 비밀번호
            if(passwordEncoder.matches(userDto.getUserPwd(), pwdById)){
                //유저아이디, 유저유형 가져오기
                userDto=loginMapper.signIn(userDto);
                //비번 일치했을 경우
                //토큰 발급
                String token = tokenProvider.createToken(String.format("%s:%s", userDto.getUserId(), userDto.getUserType()));
                userDto.setToken(token);
                //리프레시 토큰이 이미 있을 경우 토큰을 갱신하고 없을 경우 토큰 추가
                int cnt = refreshTokenMapper.selectRefreshToken(userDto.getUserId());
                userDto.setRefreshToken(tokenProvider.createRefreshToken());
                if(cnt != 0){
                    //리프레시 토큰 갱신
                    refreshTokenMapper.updateRefreshToken(userDto);
                }else{
                    //리프레시 토큰 발급
                    refreshTokenMapper.insertRefreshToken(userDto);
                }
                //반영결과 리턴
                return userDto;
            }else{
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }//비번일치확인 if end
        }else{
            throw new IllegalArgumentException("아이디가 일치하지 않습니다.");
        }//아이디확인 if end
    }//signIn end

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
