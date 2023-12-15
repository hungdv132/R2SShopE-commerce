package org.hurc.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hurc.cms.entity.Course;
import org.hurc.cms.entity.User;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseDto {
  private Long id;
  private Date regisDate;
  private BigDecimal purchase;
  private User user;
  private Course course;
}
