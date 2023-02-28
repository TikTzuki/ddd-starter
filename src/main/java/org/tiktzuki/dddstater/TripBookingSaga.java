package org.tiktzuki.dddstater;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowException;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.tiktzuki.dddstater.domains.trip.service.TripBookingActivities;
import org.tiktzuki.dddstater.domains.trip.service.impl.TripBookingActivitiesImpl;
import org.tiktzuki.dddstater.domains.trip.workflow.TripBookingWorkflow;
import org.tiktzuki.dddstater.domains.trip.workflow.impl.TripBookingWorkflowImpl;

public class TripBookingSaga {

    static final String TASK_QUEUE = "TripBooking";

    @SuppressWarnings("CatchAndPrintStackTrace")
    public static void main(String[] args) {
        // gRPC stubs wrapper that talks to the local docker instance of temporal service.
        WorkflowServiceStubs service = WorkflowServiceStubs.newServiceStubs(WorkflowServiceStubsOptions.newBuilder()
                .setTarget("10.8.0.2:7233")
                .build());
        // client that can be used to start and signal workflows
        WorkflowClient client = WorkflowClient.newInstance(service);

        // worker factory that can be used to create workers for specific task queues
        WorkerFactory factory = WorkerFactory.newInstance(client);

        // Worker that listens on a task queue and hosts both workflow and activity implementations.
        Worker worker = factory.newWorker(TASK_QUEUE);

        // Workflows are stateful. So you need a type to create instances.
        worker.registerWorkflowImplementationTypes(TripBookingWorkflowImpl.class);

        // Activities are stateless and thread safe. So a shared instance is used.
        TripBookingActivities tripBookingActivities = new TripBookingActivitiesImpl();
        worker.registerActivitiesImplementations(tripBookingActivities);

        // Start all workers created by this factory.
        factory.start();
        System.out.println("Worker started for task queue: " + TASK_QUEUE);

        // now we can start running instances of our saga - its state will be persisted
        WorkflowOptions options = WorkflowOptions.newBuilder().setTaskQueue(TASK_QUEUE).build();
        TripBookingWorkflow trip1 = client.newWorkflowStub(TripBookingWorkflow.class, options);
        try {
            trip1.bookTrip("trip1");
        } catch (WorkflowException e) {
            // Expected
        }

        try {
            TripBookingWorkflow trip2 = client.newWorkflowStub(TripBookingWorkflow.class, options);
            trip2.bookTrip("trip2");
        } catch (WorkflowException e) {
            // Expected
        }

        System.exit(0);
    }
}