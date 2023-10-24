package osc.consumer.Impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import osc.consumer.EmailConsumer;
import osc.dto.OrderDto;
import osc.entity.Email;
import osc.service.MailSenderService;

import javax.mail.MessagingException;

@Service
public class EmailConsumerImpl implements EmailConsumer {
   // @Autowired
    private final MailSenderService mailSenderService;
    String subject = "Thank you for your TrustShoppingUSA purchase!";
    Email email = new Email ();
    public EmailConsumerImpl (MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    @KafkaListener(
            topics = "order-email-events",
            containerFactory = "mailKafkaListenerContainerFactory",
            groupId = "pm")
    @Override
    public void receiveMessageFromOrder (OrderDto orderDto) throws MessagingException {
//
        email.setTo (orderDto.getUserId ());
        email.setSubject (subject);
        email.setBody (mailSenderService.orderConfirmed (orderDto));
        mailSenderService.send (email);
    }
//    @Override
//    public void processOrderEvent(OrderEvent orderEvent) throws MessagingException {
//        if (orderEvent.getEventType ().equals (EventType.ORDER_CREATED)) {
//            OrderDto orderDto = orderEvent.getOrderDto ();
//            email.setTo (orderDto.getUserId ());
//            email.setSubject (subject);
//            email.setBody (mailSenderService.orderConfirmed (orderDto));
//            mailSenderService.send (email);
//        } else if (orderEvent.getEventType ().equals (EventType.ORDER_CANCELED)) {
//            OrderDto orderDto = orderEvent.getOrderDto ();
//            email.setTo (orderDto.getUserId ());
//            email.setSubject (subject);
//            email.setBody (mailSenderService.orderCanceled (orderDto));
//            mailSenderService.send (email);
//        }
//    }
}
