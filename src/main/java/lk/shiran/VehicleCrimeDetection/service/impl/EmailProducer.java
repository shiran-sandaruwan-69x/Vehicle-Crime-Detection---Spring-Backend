package lk.shiran.VehicleCrimeDetection.service.impl;

import lk.shiran.VehicleCrimeDetection.dto.EmailMessage;
import lk.shiran.VehicleCrimeDetection.security.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendEmail(EmailMessage message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE, message);
    }
}
