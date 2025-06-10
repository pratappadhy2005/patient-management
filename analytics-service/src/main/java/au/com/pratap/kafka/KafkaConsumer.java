package au.com.pratap.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaConsumer {
    Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "patient", groupId = "analytics-service")
    public void consumerEvent(byte[] event) {
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            // ... Perform any business related to analytics here
            logger.info("Event received: {} - {}",
                    patientEvent.getEventType(),
                    patientEvent.getEmail());
        } catch (InvalidProtocolBufferException e) {
            logger.error("Error deserializing event {}", e.getMessage());
        }
    }
}
