package org.tiktzuki.dddstater.domains.trip.workflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface TripBookingWorkflow {
    @WorkflowMethod
    void bookTrip(String name);
}
