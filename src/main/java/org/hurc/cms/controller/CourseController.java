package org.hurc.cms.controller;

import lombok.RequiredArgsConstructor;
import org.hurc.cms.dto.CourseDto;
import org.hurc.cms.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {
  private final CourseService courseService;

  @GetMapping
  public ResponseEntity<List<CourseDto>> getAllCourses() {
    return ResponseEntity.ok(courseService.getAllCourses());
  }

  @PostMapping
  public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
    return new ResponseEntity<>(courseService.createCourse(courseDto), HttpStatus.CREATED);
  }

  @PutMapping("{id}")
  public ResponseEntity<CourseDto> updateCourse(@PathVariable("id") Long courseId, @RequestBody CourseDto courseDto) {
    return ResponseEntity.ok(courseService.updateCourse(courseId, courseDto));
  }
}
