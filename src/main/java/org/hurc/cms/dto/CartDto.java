package org.hurc.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hurc.cms.entity.CartLineItem;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
  private Long id;
  private Date createdDate;
  private List<CartLineItem> cartLineItems;
}
