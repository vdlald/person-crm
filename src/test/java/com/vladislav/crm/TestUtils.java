package com.vladislav.crm;

import com.vladislav.crm.entities.AbstractEntity;
import com.vladislav.crm.entities.Company;
import com.vladislav.crm.entities.Contact;
import com.vladislav.crm.entities.User;

import java.lang.reflect.InvocationTargetException;

public class TestUtils {

    private TestUtils() {
    }

    public static User getUser(Long id) {
        return getEntity(id, User.class);
    }

    public static Company getCompany(Long id) {
        return getEntity(id, Company.class);
    }

    public static Contact getContact(Long id) {
        return getEntity(id, Contact.class);
    }

    private static <T extends AbstractEntity> T getEntity(Long id, Class<T> clazz) {
        final T entity;
        try {
            entity = clazz.getConstructor().newInstance();
            entity.setId(id);
            return entity;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.err.println("Entity require NoArgsConstructor");
            e.printStackTrace();
            return null;
        }
    }
}
