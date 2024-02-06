package com.jwtproject.service;

import com.jwtproject.dto.UserDto;
import com.jwtproject.mapper.AdminMapper;
import com.jwtproject.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;

    //유저 조회
    public List<UserDto> selectUserList(){
        return adminMapper.selectUserList();
    }
}
