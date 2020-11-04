package com.vladislav.crm;

import com.vladislav.crm.entities.User;

public class IntegrationTestUtils {

    private IntegrationTestUtils() {
    }

    public static User getUser() {
        return new User()
                .setUsername("username")
                .setPassword("$2a$10$hGueLku/zBqQz7LEmko3PeO7rOhGA0Yp8wMo.5uVdxKyNNHDUAISu");
    }
}
