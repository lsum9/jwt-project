package com.jwtproject.mapper;

import com.jwtproject.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Mapper
public interface RefreshTokenMapper {

    String selectRefreshToken(String userId);
    int insertRefreshToken(UserDto userDto);

    int updateRefreshToken(UserDto userDto);

}
