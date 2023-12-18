package org.hurc.cms.service.impl;

import lombok.RequiredArgsConstructor;
import org.hurc.cms.dto.UserCourseDto;
import org.hurc.cms.entity.Course;
import org.hurc.cms.entity.User;
import org.hurc.cms.entity.UserCourse;
import org.hurc.cms.exception.CmsException;
import org.hurc.cms.exception.ResourceNotFoundException;
import org.hurc.cms.repository.CourseRepository;
import org.hurc.cms.repository.UserCourseRepository;
import org.hurc.cms.security.CustomUserDetails;
import org.hurc.cms.service.AssignmentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {
  private final CourseRepository courseRepository;
  private final UserCourseRepository userCourseRepository;
  private final ModelMapper modelMapper;

  @Override
  public UserCourseDto assignCourse(Long courseId, UserCourseDto userCourseDto) {
    User user =
        ((CustomUserDetails) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal()).getUser();

    Course course = courseRepository.findById(courseId).orElseThrow(
        () -> new ResourceNotFoundException("Course not exists with id: " + courseId)
    );

    UserCourse userCourse = new UserCourse();

    userCourse.setCourse(course);
    userCourse.setUser(user);
    userCourse.setPurchase(userCourseDto.getPurchase());
    userCourse.setRegisDate(userCourseDto.getRegisDate());

    UserCourse newUserCourse = userCourseRepository.save(userCourse);

    return modelMapper.map(newUserCourse, UserCourseDto.class);
  }

  @Override
  public String unAssignCourse(Long assigmentId) {

    User user =
        ((CustomUserDetails) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal()).getUser();

    UserCourse userCourse = userCourseRepository.findUserCourseByIdAndUser(assigmentId, user)
        .orElseThrow(
            () -> new CmsException(HttpStatus.BAD_REQUEST, "User is not assigned with assignment id:" + assigmentId)
        );

    userCourseRepository.delete(userCourse);
    return "User is unassigned successfully";
  }

  @Override
  public List<UserCourseDto> getAllAssignment() {
    return userCourseRepository
        .findAll()
        .stream()
        .map(uc -> modelMapper.map(uc, UserCourseDto.class)).toList();
  }
}
