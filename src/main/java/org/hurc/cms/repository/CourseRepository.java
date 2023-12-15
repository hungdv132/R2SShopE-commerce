package org.hurc.cms.repository;

import jakarta.websocket.server.PathParam;
import org.hurc.cms.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course, Long> {
  @Query(nativeQuery = true,
      value = """
          select exists(
          select 1 from user_courses uc join courses c on uc.course_id = c.id
          where uc.user_id =:userId and now() between start_date and end_date);""")
  int checkAccountExist(@PathParam("userId") Long userId);
}
