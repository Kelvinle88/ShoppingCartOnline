package osc.service.Impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import osc.dto.OrderDto;
import osc.enums.PaymentStatus;
import osc.entity.Payment;
import osc.enums.OrderStatus;
import osc.publisher.PaymentPublisher;
import osc.repository.PaymentRepository;
import osc.service.PaymentService;
import osc.utils.CustomerUtil;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Value("${stripe.key.secret}")
    String STRIPE_API_KEY;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentPublisher paymentPublisher;
    @Override
    public String CheckOut(OrderDto orderDTO) throws StripeException {
        Stripe.apiKey = STRIPE_API_KEY;
        // Start by finding existing customer or creating a new one if needed
        Customer customer = CustomerUtil.findOrCreateCustomer(orderDTO.getUserId());

        // Create a PaymentIntent and send its client secret to the client
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount((long) (orderDTO.getTotal().doubleValue() * 100L))
                .setCurrency("usd")
                .setCustomer(customer.getId())
                .setAutomaticPaymentMethods(PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                        .setEnabled(true)
                        .build()
                )
                .build();

        try {
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            // Update the payment and order status based on the payment result
           // Payment payment = paymentRepository.findByOrderId(orderDTO.getId());

            //if (payment == null) {
                Payment newPayment = new Payment();
                newPayment.setPaymentId(paymentIntent.getId());
                newPayment.setOrderId(orderDTO.getId());
                //newPayment.setStatus (PaymentStatus.PENDING);
                // Save the new payment entity
                //paymentRepository.save(newPayment);
           // } else {
                //paymentIntent.setStatus ("succeeded");
                paymentIntent.setStatus ("succeeded");
                if ("succeeded".equals(paymentIntent.getStatus())) {
                    newPayment.setStatus(PaymentStatus.CONFIRMED);
                    paymentRepository.save(newPayment);
                    orderDTO.setOrderStatus(OrderStatus.CONFIRMED);
                    orderDTO.setPaymentStatus (PaymentStatus.CONFIRMED);
                } else {
                    newPayment.setStatus(PaymentStatus.REJECTED);
                    paymentRepository.save(newPayment);
                    orderDTO.setOrderStatus(OrderStatus.PROCESSING);
                    orderDTO.setPaymentStatus (PaymentStatus.REJECTED);
               // }
            }
            paymentPublisher.updateOrderPayment (orderDTO);
            return paymentIntent.getStatus ();
        } catch (StripeException e) {
            throw e;
        }
    }


}