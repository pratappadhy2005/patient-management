package au.com.pratap.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClient {
    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);
    private final BillingServiceGrpc.BillingServiceBlockingStub billingServiceBlockingStub;

    public BillingServiceGrpcClient(@Value("${billing.service.address:localhost}") String billingServiceAddress,
                                    @Value("${billing.service.grpc.port:9001}") int billingServicePort) {
        log.info("Connecting to Billing Service at {}:{}", billingServiceAddress, billingServicePort);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(billingServiceAddress, billingServicePort)
                .usePlaintext() // Use plaintext for simplicity; consider using TLS in production
                .build();
        billingServiceBlockingStub = BillingServiceGrpc.newBlockingStub(channel);
    }

    /**
     * Creates a billing account for a patient.
     *
     * This method sends a request to the billing service to create a billing account
     *
     * @param patientId
     * @param name
     * @param email
     * @return
     */
    public BillingResponse createBillingAccount(String patientId, String name, String email){
        BillingRequest request = BillingRequest.newBuilder()
                .setPatientId(patientId)
                .setName(name)
                .setEmail(email)
                .build();
        BillingResponse response = billingServiceBlockingStub.createBillingAccount(request);
        log.info("Received response from Billing Service via GRPC : {}", response);
        return response;
    }
}
