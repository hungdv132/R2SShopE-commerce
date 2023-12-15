package org.hurc.cms.repository;

import org.hurc.cms.entity.User;
import org.hurc.cms.entity.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCourseRepository extends JpaRepository<UserCourse,Long> {
  Optional<UserCourse> findUserCourseByIdAndUser(Long id, User user);
}
