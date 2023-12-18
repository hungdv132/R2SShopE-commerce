package org.hurc.cms.service.impl;

import lombok.RequiredArgsConstructor;
import org.hurc.cms.dto.CartLineItemDto;
import org.hurc.cms.entity.Cart;
import org.hurc.cms.entity.CartLineItem;
import org.hurc.cms.entity.User;
import org.hurc.cms.entity.VariantProduct;
import org.hurc.cms.exception.ResourceNotFoundException;
import org.hurc.cms.repository.CartLineItemRepository;
import org.hurc.cms.repository.CartRepository;
import org.hurc.cms.repository.VariantProductRepository;
import org.hurc.cms.security.CustomUserDetails;
import org.hurc.cms.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
  private final CartRepository cartRepository;
  private final CartLineItemRepository cartLineItemRepository;
  private final VariantProductRepository variantProductRepository;
  private final ModelMapper modelMapper;

  @Override
  public List<CartLineItemDto> getItems() {
    User user =
        ((CustomUserDetails) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal()).getUser();

    Cart cart = cartRepository.findCartByUserId(user.getId()).orElseThrow(
        () -> new ResourceNotFoundException("Cart not found with user"));

    return cart.getCartLineItems()
        .stream()
        .filter(item -> !item.isDeleted())
        .map(item -> modelMapper.map(item, CartLineItemDto.class))
        .toList();
  }

  @Override
  @Transactional
  public CartLineItemDto addItem(Long variantProductId, CartLineItemDto cartLineItemDto) {
    User user =
        ((CustomUserDetails) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal()).getUser();

    Cart cart = cartRepository.findCartByUserId(user.getId()).orElseGet(() -> {
      Cart newCart = new Cart();
      newCart.setUser(user);
      return cartRepository.save(newCart);
    });

    VariantProduct variantProduct = variantProductRepository.findById(variantProductId).orElseThrow(
        () -> new ResourceNotFoundException("Product is not found with id: " + variantProductId)
    );

    CartLineItem item = cart.getCartLineItems().stream()
        .filter(lineItem -> Objects.equals(lineItem.getVariantProduct(), variantProduct))
        .findFirst()
        .orElseGet(CartLineItem::new);

    item.setQuantity(item.getQuantity() + cartLineItemDto.getQuantity());
    item.setVariantProduct(variantProduct);
    item.setCart(cart);

    CartLineItem newItem = cartLineItemRepository.save(item);

    return modelMapper.map(newItem, CartLineItemDto.class);
  }
}
