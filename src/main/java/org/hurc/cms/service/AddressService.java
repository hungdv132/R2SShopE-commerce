package org.hurc.cms.service;

import org.hurc.cms.dto.AddressDto;

import java.util.List;

public interface AddressService {
  List<AddressDto> getAllAddresses();
  AddressDto createAddress(AddressDto addressDto);

  String deleteAddress(Long addressId);

  AddressDto updateAddress(Long addressId ,AddressDto addressDto);
}
