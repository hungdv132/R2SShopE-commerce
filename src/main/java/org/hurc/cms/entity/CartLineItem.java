package org.hurc.cms.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_line_items")
public class CartLineItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "cart_id", referencedColumnName = "id")
  private Cart cart;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "variant_product_id", referencedColumnName = "id")
  private VariantProduct variantProduct;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "order_id", referencedColumnName = "id")
  private Order order;

  @Column(nullable = false)
  private int quantity = 0;

  @Column(name = "total_price", nullable = false)
  private BigDecimal totalPrice;

  @Column(name = "added_date", nullable = false)
  private Date addedDate;

  @Column
  private boolean isDeleted = false;


  @PrePersist
  public void prePersist() {
    if (this.addedDate == null) addedDate = new Date();
    totalPrice = BigDecimal.valueOf(quantity).multiply(variantProduct.getPrice());
  }

  @PreUpdate
  public void preUpdate() {
    totalPrice = BigDecimal.valueOf(quantity).multiply(variantProduct.getPrice());
  }
}
