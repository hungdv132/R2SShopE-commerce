package org.hurc.cms.service;

import org.hurc.cms.dto.CartLineItemDto;

import java.util.List;

public interface CartService {
  List<CartLineItemDto> getItems();
  CartLineItemDto addItem(Long variantProductId, CartLineItemDto cartLineItemDto);
}
