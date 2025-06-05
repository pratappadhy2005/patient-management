package au.com.pratap.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(
            BillingGrpcService.class);
    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest, StreamObserver<BillingResponse> responseObserver) {
        // Implementation of the createBillingAccount method
        log.info("Received billing request for account: {}", billingRequest.toString());

        //Business logic to create a billing account would go here
        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("34234234")
                .setStatus("Account created successfully").build();

        //send the response back to the client
        responseObserver.onNext(response);
        //response is completed so end the cycle
        responseObserver.onCompleted();
    }
}
