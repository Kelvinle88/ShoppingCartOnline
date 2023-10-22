package osc.service;

import osc.dto.OrderDto;
import osc.entity.Email;

public interface MailSenderService {
    public void send(Email email);
    public String orderDtoToTableString(OrderDto orderDto);
//    public void sendPlainTextEmail(Email email) throws MessagingException;
//    public void sendEmail(Email email);
}
