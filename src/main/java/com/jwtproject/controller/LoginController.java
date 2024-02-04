package com.jwtproject.controller;

import com.jwtproject.dto.UserDto;
import com.jwtproject.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @Operation(summary = "회원 가입")
    @PostMapping("/sign-up")
    public ResponseEntity<Integer> signUp(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(loginService.signUp(userDto));
    }

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public ResponseEntity<UserDto> signIn(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(loginService.signIn(userDto));
    }

}
