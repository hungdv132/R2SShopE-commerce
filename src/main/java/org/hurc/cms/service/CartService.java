package org.hurc.cms.service;

import org.hurc.cms.dto.CartLineItemDto;

public interface CartService {
  CartLineItemDto addItem(Long variantProductId, CartLineItemDto cartLineItemDto);
}
