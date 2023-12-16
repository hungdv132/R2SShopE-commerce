package org.hurc.cms.repository;

import org.hurc.cms.entity.CartLineItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartLineItemRepository extends JpaRepository<CartLineItem,Long> {
}
