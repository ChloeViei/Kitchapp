// package com.chloeviei.spring.auth.controllers;

// import java.net.http.HttpHeaders;
// import java.util.HashSet;
// import java.util.List;
// import java.util.Objects;
// import java.util.Set;
// import java.util.stream.Collectors;

// import javax.validation.Valid;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.authentication.DisabledException;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RestController;

// import com.chloeviei.spring.auth.models.ERole;
// import com.chloeviei.spring.auth.models.Role;
// import com.chloeviei.spring.auth.models.User;
// import com.chloeviei.spring.auth.payload.request.JwtRequest;
// import com.chloeviei.spring.auth.payload.request.LoginRequest;
// import com.chloeviei.spring.auth.payload.request.SignupRequest;
// import com.chloeviei.spring.auth.payload.request.TokenRefreshRequest;
// import com.chloeviei.spring.auth.payload.response.JwtResponse;
// import com.chloeviei.spring.auth.payload.response.MessageResponse;
// import com.chloeviei.spring.auth.repository.RoleRepository;
// import com.chloeviei.spring.auth.repository.UserRepository;
// import com.chloeviei.spring.auth.security.jwt.JwtTokenUtil;
// import com.chloeviei.spring.auth.security.services.UserDetailsImpl;

// @RestController
// @CrossOrigin
// @RequestMapping("/api/auth")
// public class JwtAuthenticationController {

// 	@Autowired
// 	private AuthenticationManager authenticationManager;

// 	@Autowired
// 	UserRepository userRepository;

// 	@Autowired
// 	RoleRepository roleRepository;

// 	@Autowired
//   	PasswordEncoder encoder;

// 	@Autowired
// 	private JwtTokenUtil jwtTokenUtil;

// 	// @Autowired
// 	// private UserDetailsService jwtInMemoryUserDetailsService;

// 	@PostMapping("/signin")
// 	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest authenticationRequest) {
// 		Authentication authentication = authenticationManager
// 			.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
// 		SecurityContextHolder.getContext().setAuthentication(authentication);
// 		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
// 		List<String> roles = userDetails.getAuthorities().stream()
// 			.map(item -> item.getAuthority())
// 			.collect(Collectors.toList());

// 		final String token = jwtTokenUtil.generateToken(userDetails);
		
// 		return ResponseEntity.ok(new JwtResponse(token, refreshToken.getToken(), userDetails.getId(),
//         	userDetails.getUsername(), userDetails.getEmail(), roles));
// 	}

// 	@PostMapping("/signup")
// 	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
// 		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
// 		}

// 		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
// 			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
// 		}
// 		// Create new user's account
// 		User user = new User(signUpRequest.getUsername(),
// 							signUpRequest.getEmail(),
// 							encoder.encode(signUpRequest.getPassword()));

// 		Set<String> strRoles = signUpRequest.getRole();
// 		Set<Role> roles = new HashSet<>();

// 		if (strRoles == null) {
// 			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
// 				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
// 			roles.add(userRole);
// 		} else {
// 			strRoles.forEach(role -> {
// 				switch (role) {
// 				case "admin":
// 				Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
// 					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
// 				roles.add(adminRole);
// 				break;
// 				case "mod":
// 				Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
// 					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
// 				roles.add(modRole);
// 				break;
// 				default:
// 				Role userRole = roleRepository.findByName(ERole.ROLE_USER)
// 					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
// 				roles.add(userRole);
// 				}
// 			});
// 		}
// 		user.setRoles(roles);
// 		userRepository.save(user);

// 		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
// 	}

	
// 	@PostMapping("/signout")
// 	public ResponseEntity<?> logoutUser() {
// 		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
// 		Long userId = userDetails.getId();
// 		refreshTokenService.deleteByUserId(userId);
// 		return ResponseEntity.ok(new MessageResponse("Log out successful!"));
// 	}

// 	// @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
// 	// public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
// 	// 		throws Exception {

// 	// 	authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

// 	// 	final UserDetails userDetails = jwtInMemoryUserDetailsService
// 	// 			.loadUserByUsername(authenticationRequest.getUsername());

// 	// 	final String token = jwtTokenUtil.generateToken(userDetails);

// 	// 	return ResponseEntity.ok(new JwtResponse(token));
// 	// }

// 	// private void authenticate(String username, String password) throws Exception {
// 	// 	Objects.requireNonNull(username);
// 	// 	Objects.requireNonNull(password);
// 	// 	try {
// 	// 		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
// 	// 	} catch (DisabledException e) {
// 	// 		throw new Exception("USER_DISABLED", e);
// 	// 	} catch (BadCredentialsException e) {
// 	// 		throw new Exception("INVALID_CREDENTIALS", e);
// 	// 	}
// 	// }
// }