package com.jwtproject.mapper;

import com.jwtproject.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    int signUp(UserDto userDto);

    UserDto signIn(UserDto userDto);

    UserDto idCheck(UserDto userDto);

    String pwdCheck(UserDto userDto);


    //로그인
    //아이디로 비밀번호 조회
    String pwdById(UserDto userDto);

    //아이디로 비번조회되면 비번일치확인
    UserDto pwdMatch(UserDto userDto);

}
