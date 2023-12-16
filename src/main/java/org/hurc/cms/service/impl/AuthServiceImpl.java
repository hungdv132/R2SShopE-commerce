package org.hurc.cms.service.impl;

import lombok.AllArgsConstructor;
import org.hurc.cms.dto.LoginDto;
import org.hurc.cms.dto.RegisterDto;
import org.hurc.cms.entity.Cart;
import org.hurc.cms.entity.Role;
import org.hurc.cms.entity.User;
import org.hurc.cms.exception.CmsException;
import org.hurc.cms.repository.CartRepository;
import org.hurc.cms.repository.RoleRepository;
import org.hurc.cms.repository.UserRepository;
import org.hurc.cms.security.JwtTokenProvider;
import org.hurc.cms.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
  private UserRepository userRepository;
  private RoleRepository roleRepository;
  private PasswordEncoder passwordEncoder;
  private AuthenticationManager authenticationManager;
  private JwtTokenProvider jwtTokenProvider;
  private CartRepository cartRepository;
  @Override
  public String register(RegisterDto registerDto) {
    // check username is already exists in database
    if(Boolean.TRUE.equals(userRepository.existsByUsername(registerDto.getUsername()))){
      throw new CmsException(HttpStatus.BAD_REQUEST, "Username already exists!");
    }

    // check email is already exists in database
    if(Boolean.TRUE.equals(userRepository.existsByEmail(registerDto.getEmail()))){
      throw new CmsException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
    }

    User user = new User();
    user.setName(registerDto.getName());
    user.setUsername(registerDto.getUsername());
    user.setEmail(registerDto.getEmail());
    user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByName("ROLE_USER");
    roles.add(userRole);

    user.setRoles(roles);

    User newUser = userRepository.save(user);


    Cart cart = new Cart();
    cart.setUser(newUser);
    cartRepository.save(cart);


    return "User Registered Successfully!.";
  }

  @Override
  public String login(LoginDto loginDto) {
    // TODO: try catch login for wrong username or password
    var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        loginDto.getUsernameOrEmail(),
        loginDto.getPassword()
    ));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    return jwtTokenProvider.generateToken(authentication);
  }


}
