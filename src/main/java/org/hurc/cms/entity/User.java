package org.hurc.cms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false, unique = true)
  private String email;

  @Column
  private boolean status;

  @Column(name = "created_date")
  private Date createdDate;

  @Column(nullable = false)
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
  )
  private Set<Role> roles;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
  private List<Address> addresses = new ArrayList<>();

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
  private Cart cart;

  @PrePersist
  public void prePersist() {
    this.createdDate = new Date();
    this.status = true;
  }


}
