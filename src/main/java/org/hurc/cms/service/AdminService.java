package org.hurc.cms.service;

import org.hurc.cms.dto.UserDto;

import java.util.List;

public interface AdminService {
  List<UserDto> getAllUsers();
  String deleteUser(Long userId);
  String activateUser(Long userId);
  String deactivateUser(Long userId);
}
