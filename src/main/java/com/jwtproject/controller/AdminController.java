package com.jwtproject.controller;

import com.jwtproject.common.AdminAuthorize;
import com.jwtproject.dto.UserDto;
import com.jwtproject.service.AdminService;
import com.jwtproject.security.CookieUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "관리자용 API")
@AdminAuthorize
@RestController
@RequiredArgsConstructor
@RequestMapping(value="/admin")
public class AdminController {
    private final AdminService adminService;
    @Operation(summary = "관리자 사용자 조회")
    @GetMapping("/userList")
    public ResponseEntity<List<UserDto>> userList() {

        return ResponseEntity.ok(adminService.selectUserList());
    }

}
