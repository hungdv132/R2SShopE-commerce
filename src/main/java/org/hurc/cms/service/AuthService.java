package org.hurc.cms.service;

import org.hurc.cms.dto.LoginDto;
import org.hurc.cms.dto.RegisterDto;

public interface AuthService {
  String register(RegisterDto registerDto);
  String login(LoginDto loginDto);
}