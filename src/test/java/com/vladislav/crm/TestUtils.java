package com.vladislav.crm;

import com.vladislav.crm.entities.User;

public class TestUtils {

    private TestUtils() {
    }

    public static User getUser(Long id) {
        final User user = new User();
        user.setId(id);
        return user;
    }
}
