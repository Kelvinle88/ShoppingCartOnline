package osc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import osc.entity.Email;

@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendNewMail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getTo ());
        message.setSubject(email.getSubject ());
        message.setText(email.getBody ());
        mailSender.send(message);
    }
}
