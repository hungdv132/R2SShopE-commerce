package org.hurc.cms.controller;

import lombok.AllArgsConstructor;
import org.hurc.cms.dto.JwtAuthResponse;
import org.hurc.cms.dto.LoginDto;
import org.hurc.cms.dto.RegisterDto;
import org.hurc.cms.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private AuthService authService;

  // Build Register REST API
  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
    String response = authService.register(registerDto);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  // Build Login REST API
  @PostMapping("/login")
  public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
    String token = authService.login(loginDto);

    JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
    jwtAuthResponse.setAccessToken(token);

    return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
  }

}