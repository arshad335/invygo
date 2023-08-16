package com.example.invygo.service;

import com.example.invygo.entity.User;
import com.example.invygo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User userEntity = userRepository.findByUsername(username);
    if (userEntity == null) {
      throw new UsernameNotFoundException("UserEntity not found");
    }
    return new org.springframework.security.core.userdetails.User(userEntity.getUsername(),
            userEntity.getPassword(), getAuthorities(userEntity));
  }

  private List<GrantedAuthority> getAuthorities(User userEntity) {
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    userEntity
        .getRoles()
        .forEach(
            role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase())));

    return authorities;
  }
}
