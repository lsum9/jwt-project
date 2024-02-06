package com.jwtproject.service;

import com.jwtproject.dto.UserDto;
import com.jwtproject.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    //유저정보 조회
    @Transactional(readOnly = true)
    public UserDto getUserInfo(String userId) {
        UserDto userInfo = userMapper.selectUserInfo(userId);
        if(userInfo == null){
            throw new NoSuchElementException("존재하지 않는 회원입니다.");
        }else{
            return userInfo;
        }
    }

    //회원탈퇴
    @Transactional
    public int deleteUser(String userId) {
        int delCnt = userMapper.deleteUser(userId);
        if(delCnt == 0){
            throw new NoSuchElementException("회원탈퇴에 실패했습니다.");
        }else{
            return delCnt;
        }
    }

    //회원정보 수정
    @Transactional
    public int updateUser(String userId, UserDto userDto) {
        int updateCnt = userMapper.updateUser(userId, userDto);

        //비번확인
        if(updateCnt == 0){
            throw new NoSuchElementException("회원탈퇴에 실패했습니다.");
        }else{
            return updateCnt;
        }
        //throw new NoSuchElementException("아이디 또는 비밀번호가 일치하지 않습니다.");
    }
}
