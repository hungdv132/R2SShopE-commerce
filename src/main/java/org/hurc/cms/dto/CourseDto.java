package org.hurc.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
  private Long id;
  private String name;
  private String description;
  private Date startDate;
  private Date endDate;
  private BigDecimal price;
  private CategoryDto category;
}
