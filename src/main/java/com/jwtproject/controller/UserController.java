package com.jwtproject.controller;

import com.jwtproject.common.UserAuthorize;
import com.jwtproject.dto.UserDto;
import com.jwtproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@Tag(name = "로그인 후 사용할 수 있는 API")
@RequiredArgsConstructor
@UserAuthorize
@RestController
@RequestMapping("/User")
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원 정보 조회")
    @GetMapping
    public UserDto getUserInfo(@AuthenticationPrincipal User user) {
        return userService.getUserInfo(user.getUsername());
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping
    public int deleteUser(@AuthenticationPrincipal User user) {
        return userService.deleteUser(user.getUsername());
    }

    @Operation(summary = "회원 정보 수정")
    @PutMapping
    public int updateUser(@AuthenticationPrincipal User user, @RequestBody UserDto userDto) {
        return userService.updateUser(user.getUsername(), userDto);
    }
}
