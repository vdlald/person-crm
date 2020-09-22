package com.vladislav.crm.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestUserAndPipelineRelationshipsConsistency {

    private User user;
    private Pipeline pipeline;

    @BeforeEach
    public void setUp() {
        user = new User();
        pipeline = new Pipeline();
    }

    @Test
    public void setUser() {
        pipeline.setUser(user);
        checkAdd();

        pipeline.setUser(null);
        checkRemove();
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
