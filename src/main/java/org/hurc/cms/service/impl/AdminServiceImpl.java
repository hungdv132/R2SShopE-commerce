package org.hurc.cms.service.impl;

import lombok.RequiredArgsConstructor;
import org.hurc.cms.dto.UserDto;
import org.hurc.cms.entity.User;
import org.hurc.cms.exception.ResourceNotFoundException;
import org.hurc.cms.repository.CourseRepository;
import org.hurc.cms.repository.UserRepository;
import org.hurc.cms.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

  private final UserRepository userRepository;
  private final CourseRepository courseRepository;
  private final ModelMapper modelMapper;

  @Override
  @Transactional
  public List<UserDto> getAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
  }

  @Override
  public String deleteUser(Long userId) {
    userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User is not exits with id: " + userId));

    int isAssignment = courseRepository.checkAccountExist(userId);

    if (isAssignment > 0) throw new ResourceNotFoundException("User is assigned");

    return "User is deleted successfully";
  }

  @Override
  public String activateUser(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException(
            "User is not exits with id: " + userId
        ));
    user.setStatus(true);
    userRepository.save(user);

    return "User is active";
  }

  @Override
  public String deactivateUser(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException(
            "User is not exits with id: " + userId
        ));

    user.setStatus(false);
    userRepository.save(user);
    return "User is deactivate";
  }
}
