package lk.shiran.VehicleCrimeDetection.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendUserCredentials(String toEmail, String username, String password) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your Account Created");

        message.setText(
                "Hello,\n\n" +
                        "Your account has been created.\n\n" +
                        "Username: " + username + "\n" +
                        "Password: " + password + "\n\n" +
                        "Please change your password after login."
        );

        mailSender.send(message);
    }
}
