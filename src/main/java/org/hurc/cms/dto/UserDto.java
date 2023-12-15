package org.hurc.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  private Long id;
  private String name;
  private String username;
  private String email;
  private boolean status;
  private Date createdDate;
  private Set<RoleDto> roles;
  private List<AddressDto> addresses;
}
