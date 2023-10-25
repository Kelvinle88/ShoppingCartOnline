package osc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import osc.entity.Email;
import osc.service.MailSenderService;

@RestController
@RequestMapping("/emails")
@CrossOrigin
public class MailSenderController {
    @Autowired
    private MailSenderService mailSenderService;
    @PostMapping("/send")
    public void sendNewEmail(@RequestBody Email email){
        //mailSenderService.send (email);
    }
}
