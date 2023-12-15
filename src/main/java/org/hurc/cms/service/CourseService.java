package org.hurc.cms.service;

import org.hurc.cms.dto.CourseDto;

import java.util.List;

public interface CourseService {
  List<CourseDto> getAllCourses();
  CourseDto createCourse(CourseDto courseDto);
  CourseDto updateCourse(Long courseId, CourseDto courseDto);

}
