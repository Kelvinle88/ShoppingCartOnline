package osc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import osc.entity.Email;
import osc.service.MailSenderService;

@RestController
@RequestMapping("/emails")
public class MailSenderController {
    @Autowired
    private MailSenderService mailSenderService;
    @PostMapping("/send")
    public void sendNewEmail(@RequestBody Email email){
        mailSenderService.sendNewMail (email);
    }
}
