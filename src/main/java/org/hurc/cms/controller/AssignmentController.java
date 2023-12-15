package org.hurc.cms.controller;

import lombok.RequiredArgsConstructor;
import org.hurc.cms.dto.UserCourseDto;
import org.hurc.cms.service.AssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assignment")
@RequiredArgsConstructor
public class AssignmentController {
  private final AssignmentService assignmentService;

  @PostMapping("/{courseId}")
  public ResponseEntity<UserCourseDto> assignCourse(@PathVariable Long courseId, @RequestBody UserCourseDto userCourseDto) {
    UserCourseDto userCourse = assignmentService.assignCourse(courseId, userCourseDto);
    return ResponseEntity.ok(userCourse);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> unAssignCourse(@PathVariable Long id) {
    return ResponseEntity.ok(assignmentService.unAssignCourse(id));
  }

  @GetMapping
  public ResponseEntity<List<UserCourseDto>> getAllAssignment(){
    return ResponseEntity.ok(assignmentService.getAllAssignment());
  }

}
