package com.chloeviei.spring.user;

import static org.assertj.core.api.Assertions.assertThat;
 
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.chloeviei.spring.auth.models.Role;
import com.chloeviei.spring.auth.models.User;
import com.chloeviei.spring.auth.repository.UserRepository;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
 
    @Autowired private UserRepository userRepository;
     
    @Test
    public void testCreateUser() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("123456789");
         
        User newUser = new User("admin@gmail.com", password);
        User savedUser = userRepository.save(newUser);
         
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testAssignRoleToUser() {
        long userId = 3;
        long roleId = 3;
        User user = userRepository.findById(userId).get();
        user.addRole(new Role(roleId));
        
        User updatedUser = userRepository.save(user);
        assertThat(updatedUser.getRoles()).hasSize(1);
    }

    @Test
    public void testAssignRoleToUser2() {
        long userId = 4;
        User user = userRepository.findById(userId).get();
        user.addRole(new Role(1L));
        user.addRole(new Role(2L));
    
        
        User updatedUser = userRepository.save(user);
        assertThat(updatedUser.getRoles()).hasSize(2);
    }
}
