// package com.chloeviei.spring.auth.security.services;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import com.chloeviei.spring.auth.models.User;
// import com.chloeviei.spring.auth.repository.UserRepository;

// @Service
// public class UserDetailsServiceImpl implements UserDetailsService {
    
//   @Autowired
//   private UserRepository userRepository;

//   @Override
//   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//       User user = userRepository.findByEmail(email)
//         .orElseThrow(() -> new UsernameNotFoundException("User Not Found with : " + email));
//       return UserDetailsImpl.build(user);
//   }
// }