package org.hurc.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hurc.cms.entity.Cart;
import org.hurc.cms.entity.Order;
import org.hurc.cms.entity.VariantProduct;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartLineItemDto {
  private Long id;
  private VariantProduct variantProduct;
  private int quantity = 0;
  private BigDecimal totalPrice;
  private Date addedDate;
  private boolean isDeleted = false;
  private Order order;

}
