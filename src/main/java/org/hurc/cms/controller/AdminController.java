package org.hurc.cms.controller;

import lombok.RequiredArgsConstructor;
import org.hurc.cms.dto.UserDto;
import org.hurc.cms.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

  private final AdminService adminService;

  @GetMapping("/users")
  public ResponseEntity<List<UserDto>> getAllUsers(){
    return ResponseEntity.ok(adminService.getAllUsers());
  }

  @PatchMapping("/users/{userId}/active")
  public ResponseEntity<String> activateUser(@PathVariable Long userId){
    return ResponseEntity.ok(adminService.activateUser(userId));
  }
  @PatchMapping("/users/{userId}/inactive")
  public ResponseEntity<String> deactivateUser(@PathVariable Long userId){
    return ResponseEntity.ok(adminService.deactivateUser(userId));
  }

  @DeleteMapping("/users/{userId}")
  public ResponseEntity<String> deleteUser(@PathVariable Long userId){
    return ResponseEntity.ok(adminService.deleteUser(userId));
  }
}
