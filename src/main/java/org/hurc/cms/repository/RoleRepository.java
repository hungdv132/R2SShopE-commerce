package org.hurc.cms.repository;

import org.hurc.cms.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
  Role findByName(String name);
}
