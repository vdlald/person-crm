package com.vladislav.crm.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestStatusAndPipelineRelationshipsConsistency {

    private Status status;
    private Pipeline pipeline;

    @BeforeEach
    public void setUp() {
        status = new Status();
        pipeline = new Pipeline();
    }

    @Test
    public void setPipeline() {
        status.setPipeline(pipeline);
        checkAdd();

        status.setPipeline(null);
        checkRemove();
    }

    @Test
    public void addAndRemoveStatus() {
        pipeline.addStatus(status);
        checkAdd();

        pipeline.removeStatus(status);
        checkRemove();
    }

    private void checkAdd() {
        assertEquals(pipeline, status.getPipeline());
        assertEquals(status, pipeline.getStatuses().get(0));
    }

    private void checkRemove() {
        assertTrue(pipeline.getStatuses().isEmpty());
        assertNull(status.getPipeline());
    }
}
