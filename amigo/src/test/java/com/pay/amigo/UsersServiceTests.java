package com.pay.amigo;

import com.pay.amigo.entities.Users;
import com.pay.amigo.services.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

import static java.time.Duration.ofSeconds;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTimeout;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersServiceTests {
    @Autowired
    UsersService usersService;

    @Test
    public void testGetUserById(){
        Users user = new Users();
        user.setId(1);
        user = usersService.addUser(user);

        Users user1 = usersService.getUserById(1);
        assertTrue(user1.getId() == user.getId());
    }
    @Test
    public void testgetUserById() {
        assertThrows(NoSuchElementException.class, () -> usersService.getUserById(10));
    }

    @Test
    public void testUserPaysItself() {
        Users user = new Users();
        user.setId(1);

        assertEquals(true, usersService.UserPaysItself(user, 30.0f));
    }

    @Test
    public void testUserPaysPositiveAmount() {
        assert(usersService.UserPaysPositiveAmount(new Users(), 50.0f));
    }

    @Test
    public void userPaysPositiveAmount() {
        assertTimeout(ofSeconds(2),
                () -> {
                    assert(usersService.UserPaysPositiveAmount(new Users(), 50.0f));
                }
        );
    }
}
