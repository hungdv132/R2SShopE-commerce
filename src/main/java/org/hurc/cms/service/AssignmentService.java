package org.hurc.cms.service;

import org.hurc.cms.dto.UserCourseDto;

import java.util.List;

public interface AssignmentService {
  UserCourseDto assignCourse(Long courseId, UserCourseDto userCourseDto);
  String unAssignCourse(Long assigmentId);

  List<UserCourseDto> getAllAssignment();
}
