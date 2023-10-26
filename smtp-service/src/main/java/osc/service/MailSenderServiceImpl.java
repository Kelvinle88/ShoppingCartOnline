package osc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import osc.dto.ItemDto;
import osc.dto.OrderDto;
import osc.entity.Email;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailSenderServiceImpl implements MailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;
    //private final Environment env;
@Override
@Async
public void send(Email email)  {
    try {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(email.getTo ());
        helper.setSubject(email.getSubject ());
        helper.setText(email.getBody (), true);
        javaMailSender.send(msg);
    } catch (Exception ex) {
        log.error("Error to send email", ex);
    }
}
    public String orderConfirmed(OrderDto orderDto) {
        StringBuilder tableString = new StringBuilder();
        // Append the message for the customer
        tableString.append("Dear Valued Customer,\n\n");
        tableString.append("Thank you for shopping at TrustShoppingUSA!\n");
        tableString.append("Your order has been received and is being processed.\n\n");
        tableString.append("[Notice for Ship to address orders]\n");
        tableString.append("We will send a follow-up email once your order has shipped. Please allow up to 2 business days for standard orders, and up to 1 day for priority and express orders, for your package to process before it is shipped.\n\n");
        tableString.append("[Notice for Pick up in-store orders]\n");
        tableString.append("We will send a follow-up email once your order is ready for pickup at your selected store.\n");
        tableString.append("Orders may take up to 10 business days from being placed to be received in-store.\n");
        tableString.append("All additional email communication will occur once your order is ready for pickup.\n\n");

        // Append header row
        tableString.append("+--------+--------------+-----------+\n");
        tableString.append("|   ID   | Ordered Date |   Total   |\n");
        tableString.append("+--------+--------------+-----------+\n");

        // Append order details
        tableString.append(String.format("| %6d | %12s | %9.2f |\n",
                orderDto.getId(), orderDto.getOrderedDate(), orderDto.getTotal()));
        // Append items
        tableString.append("+--------+------------------+-----------+-------------+\n");
        tableString.append("| Item ID|    Product Name  |  Quantity |  Subtotal   |\n");
        tableString.append("+--------+------------------+-----------+-------------+\n");

        for (ItemDto item : orderDto.getItems()) {
            tableString.append(String.format("| %6d | %15s | %9d | %11.2f |\n",
                    item.getId(), item.getProductDto (), item.getQuantity(), item.getSubTotal()));
        }
        tableString.append("+--------+------------------+-----------+-------------+\n");

        return tableString.toString();
    }
    public String orderCanceled(OrderDto orderDto) {
        StringBuilder tableString = new StringBuilder();
        // Append the message for the customer
        tableString.append("\nDear Valued Customer,\n\n");
        tableString.append("\nWe are sorry to inform you that we could not fulfill your order above, and it is now cancelled.\n");
        tableString.append("\nWe deeply apologize for any inconvenience this causes and hope you will shop with us again soon.\n\n");

        // Append header row
        tableString.append("+--------+--------------+-----------+\n");
        tableString.append("|   ID   | Ordered Date |   Total   |\n");
        tableString.append("+--------+--------------+-----------+\n");

        // Append order details
        tableString.append(String.format("| %6d | %12s | %9.2f |\n",
                orderDto.getId(), orderDto.getOrderedDate(), orderDto.getTotal()));
        tableString.append("+--------+------------------+-----------+-------------+\n");

        return tableString.toString();
    }


}
