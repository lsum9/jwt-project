package com.jwtproject.mapper;

import com.jwtproject.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    List<UserDto> selectUserList();

}
