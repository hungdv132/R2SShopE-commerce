package org.hurc.cms.repository;

import org.hurc.cms.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
  Optional<Cart> findCartByUserId(Long userId);
}
