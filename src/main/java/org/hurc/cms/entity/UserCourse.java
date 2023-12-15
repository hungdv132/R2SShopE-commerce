package org.hurc.cms.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
@Entity
@Table(name = "user_courses")
public class UserCourse {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "registration_date")
  private Date regisDate;

  @Column(name = "purchase_price")
  private BigDecimal purchase;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "course_id", referencedColumnName = "id")
  private Course course;

  @PrePersist
  public void prePersist(){
    if (this.regisDate == null) regisDate = new Date();
  }
}
