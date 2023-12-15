package org.hurc.cms.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.hurc.cms.dto.AddressDto;
import org.hurc.cms.entity.Address;
import org.hurc.cms.entity.User;
import org.hurc.cms.exception.CmsException;
import org.hurc.cms.repository.AddressRepository;
import org.hurc.cms.security.CustomUserDetails;
import org.hurc.cms.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
  private final AddressRepository addressRepository;
  private final ModelMapper modelMapper;


//  private User user;
//
//  @PostConstruct
//  public void init() {
//    user =
//        ((CustomUserDetails) SecurityContextHolder.getContext()
//            .getAuthentication()
//            .getPrincipal()).getUser();
//  }

  @Override
  public List<AddressDto> getAllAddresses() {
    User user =
        ((CustomUserDetails) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal()).getUser();
    List<Address> addresses = addressRepository.findAllByUserId(user.getId());
    return addresses.stream().map(address -> modelMapper.map(address, AddressDto.class)).toList();
  }

  @Override
  public AddressDto createAddress(AddressDto addressDto) {
    User user =
        ((CustomUserDetails) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal()).getUser();
    Address address = new Address();
    address.setUser(user);
    address.setLocation(addressDto.getLocation());

    Address newAddress = addressRepository.save(address);

    return modelMapper.map(newAddress, AddressDto.class);
  }

  @Override
  public String deleteAddress(Long addressId) {
    User user =
        ((CustomUserDetails) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal()).getUser();
    Address address = addressRepository.findByIdAndUserId(addressId, user.getId()).orElseThrow(
        () -> new CmsException(HttpStatus.NOT_FOUND, "Address is not found")
    );
    addressRepository.delete(address);
    return "Address is deleted successfully";
  }

  @Override
  public AddressDto updateAddress(Long addressId, AddressDto addressDto) {
    User user =
        ((CustomUserDetails) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal()).getUser();
    Address address = addressRepository.findByIdAndUserId(addressId, user.getId()).orElseThrow(
        () -> new CmsException(HttpStatus.NOT_FOUND, "Address is not found")
    );

    address.setLocation(addressDto.getLocation());

    Address updatedAddress = addressRepository.save(address);

    return modelMapper.map(updatedAddress,AddressDto.class);
  }

}
