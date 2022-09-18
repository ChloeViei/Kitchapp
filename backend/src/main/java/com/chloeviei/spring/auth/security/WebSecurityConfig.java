package com.chloeviei.spring.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.chloeviei.spring.auth.security.jwt.AuthTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    securedEnabled = true,
    jsr250Enabled = true,
    prePostEnabled = true)
public class WebSecurityConfig {

  // @Autowired
  // private UserDetailsServiceImpl userDetailsService;

  // @Autowired
	// private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  // @Autowired
	// private JwtRequestFilter jwtRequestFilter;
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
      return authenticationConfiguration.getAuthenticationManager();
  }
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    // httpSecurity.csrf().disable()
    //   // dont authenticate this particular request
    //   .authorizeRequests().antMatchers("/authenticate").permitAll().
    //   // all other requests need to be authenticated
    //   anyRequest().authenticated().and().
    //   // make sure we use stateless session; session won't be used to
    //   // store user's state.
    //   exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
    //   .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // // Add a filter to validate the tokens with every request
    // httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    // Configure AuthenticationManagerBuilder
    // AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
    // authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    // // Get AuthenticationManager
    // AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

    httpSecurity.csrf().disable();
    httpSecurity.authorizeRequests().anyRequest().permitAll();
    httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // httpSecurity
    //     .cors().and()
    //     .csrf().disable()
    //     .authorizeRequests().antMatchers("/auth/login").permitAll()
    //     // .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
    //     // .anyRequest().permitAll()
    //     .anyRequest().authenticated().and()
    //     // .addFilter(getAuthenticationFilter(authenticationManager))
    //     // .addFilter(new AuthorizationFilter(authenticationManager, userRepository))
    //     // .authenticationManager(authenticationManager);

    //     .sessionManagement()
    //     .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    // httpSecurity.headers().frameOptions().disable();

    return httpSecurity.build();
  }
}
