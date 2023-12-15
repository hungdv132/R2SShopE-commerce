package org.hurc.cms.controller;


import lombok.RequiredArgsConstructor;
import org.hurc.cms.dto.AddressDto;
import org.hurc.cms.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {
  private final AddressService addressService;

  @GetMapping
  public ResponseEntity<List<AddressDto>> getAllAddresses() {
    return ResponseEntity.ok(addressService.getAllAddresses());
  }

  @PostMapping
  public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {
    return new ResponseEntity<>(addressService.createAddress(addressDto), HttpStatus.CREATED);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
    return ResponseEntity.ok(addressService.deleteAddress(id));
  }

  @PutMapping("{id}")
  public ResponseEntity<AddressDto> createAddress(@PathVariable Long id,@RequestBody AddressDto addressDto) {
    return ResponseEntity.ok(addressService.updateAddress(id,addressDto));
  }
}
