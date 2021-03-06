package com.vladislav.crm.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestUserAndUserInfoRelationshipsConsistency {

    private User user;
    private UserInfo userInfo;

    @BeforeEach
    public void setUp() {
        user = new User();
        userInfo = new UserInfo();
    }

    @Test
    public void setUser() {
        userInfo.setUserSafe(user);
        checkAdd();

        userInfo.setUserSafe(null);
        checkRemove();
    }

    @Test
    public void setInfo() {
        user.setInfoSafe(userInfo);
        checkAdd();

        user.setInfoSafe(null);
        checkRemove();
    }

    private void checkAdd() {
        assertEquals(user, userInfo.getUser());
        assertEquals(userInfo, user.getInfo());
    }

    private void checkRemove() {
        assertNull(user.getInfo());
        assertNull(userInfo.getUser());
    }
}
