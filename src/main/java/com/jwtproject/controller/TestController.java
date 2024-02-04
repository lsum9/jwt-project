package com.jwtproject.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "예제 API", description = "Swagger 테스트용 API")
@RequestMapping("/login")
public class TestController {

    @GetMapping("/sign-in")
    public String signIn() {
        return "예시 API";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "무시되는 API";
    }
}
