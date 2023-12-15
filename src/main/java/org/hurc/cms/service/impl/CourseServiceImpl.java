package org.hurc.cms.service.impl;

import lombok.RequiredArgsConstructor;
import org.hurc.cms.dto.CourseDto;
import org.hurc.cms.entity.Category;
import org.hurc.cms.entity.Course;
import org.hurc.cms.exception.ResourceNotFoundException;
import org.hurc.cms.repository.CourseRepository;
import org.hurc.cms.repository.UserRepository;
import org.hurc.cms.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

  private final CourseRepository courseRepository;
  private final ModelMapper modelMapper;
  private final UserRepository userRepository;

  @Override
  public List<CourseDto> getAllCourses() {
    List<Course> courses = courseRepository.findAll();
    return courses.stream().map(course -> modelMapper.map(course, CourseDto.class)).toList();
  }

  @Override
  public CourseDto createCourse(CourseDto courseDto) {
    Course course = modelMapper.map(courseDto, Course.class);
    Course newCourse = courseRepository.save(course);
    return modelMapper.map(newCourse, CourseDto.class);
  }

  @Override
  public CourseDto updateCourse(Long courseId, CourseDto courseDto) {
    Course course = courseRepository.findById(courseId)
        .orElseThrow(() -> new ResourceNotFoundException("Course not exists with id: " + courseId));

    course.setName(courseDto.getName());
    course.setDescription(courseDto.getDescription());
    course.setPrice(courseDto.getPrice());
    course.setStartDate(courseDto.getStartDate());
    course.setEndDate(courseDto.getEndDate());

    if (courseDto.getCategory() != null) {
      Category category = modelMapper.map(courseDto.getCategory(), Category.class);
      course.setCategory(category);
    }

    Course updatedCourse = courseRepository.save(course);
    return modelMapper.map(updatedCourse, CourseDto.class);
  }

}
