package com.jwtproject.dto;

import lombok.Data;

@Data
public class UserDto {
    private String userId;
    private String userPwd;
    private String userType;
    private String token;
    private String userName;
    private String userEmail;
    private String refreshToken;
}
