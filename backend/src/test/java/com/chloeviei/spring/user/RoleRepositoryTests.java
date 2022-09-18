package com.chloeviei.spring.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
 
import java.util.List;
 
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.chloeviei.spring.auth.models.Role;
import com.chloeviei.spring.auth.repository.RoleRepository;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {

    @Autowired 
    private RoleRepository repo;
     
    @Test
    public void testCreateRoles() {
        Role admin = new Role("ROLE_ADMIN");
        Role moderator = new Role("ROLE_MODERATOR");
        Role user = new Role("ROLE_USER");
         
        repo.saveAll(List.of(admin, moderator, user));
         
        long count = repo.count();
        assertEquals(3, count);
    }
}