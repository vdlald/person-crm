package com.vladislav.crm.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestUserAndPipelineRelationshipsConsistency {

    private User user;
    private Pipeline pipeline;

    @BeforeEach
    public void setUp() {
        pipeline = new Pipeline();
        user = new User();
        user.setId(1L);
    }

    @Test
    public void setUser() {
        pipeline.setUser(user);
        checkAdd();

        final User user2 = new User();
        user2.setId(2L);
        pipeline.setUser(user2);
        assertTrue(user.getPipelines().isEmpty());
        assertEquals(pipeline, user2.getPipelines().get(0));

        pipeline.setUser(null);
        assertTrue(user2.getPipelines().isEmpty());
        assertNull(pipeline.getUser());
    }

    @Test
    public void addAndRemovePipeline() {
        user.addPipeline(pipeline);
        checkAdd();

        user.removePipeline(pipeline);
        checkRemove();
    }

    private void checkAdd() {
        assertEquals(user, pipeline.getUser());
        assertEquals(pipeline, user.getPipelines().get(0));
    }

    private void checkRemove() {
        assertTrue(user.getPipelines().isEmpty());
        assertNull(pipeline.getUser());
    }
}
