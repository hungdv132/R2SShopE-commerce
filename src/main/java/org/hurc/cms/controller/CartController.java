package org.hurc.cms.controller;


import lombok.RequiredArgsConstructor;
import org.hurc.cms.dto.CartLineItemDto;
import org.hurc.cms.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
  private final CartService cartService;

  @GetMapping
  public ResponseEntity<List<CartLineItemDto>> getCart(){
    return ResponseEntity.ok(cartService.getItems());
  }
  @PostMapping("{id}")
  public ResponseEntity<CartLineItemDto> addItem(@PathVariable("id") Long variantProductId,
                                                 @RequestBody CartLineItemDto cartLineItemDto){
    return ResponseEntity.ok(cartService.addItem(variantProductId,cartLineItemDto));
  }
}
