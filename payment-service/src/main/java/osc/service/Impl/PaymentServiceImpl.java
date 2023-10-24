package osc.service.Impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCancelParams;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import osc.dto.OrderDto;
import osc.entity.Payment;
import osc.enums.OrderStatus;
import osc.enums.PaymentStatus;
import osc.publisher.PaymentPublisher;
import osc.repository.PaymentRepository;
import osc.service.PaymentService;
import osc.utils.CustomerUtil;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Value("${stripe.key.secret}")
    private String STRIPE_API_KEY;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentPublisher paymentPublisher;

    @Override
    public String checkOut (OrderDto orderDTO) throws StripeException {
        Stripe.apiKey = STRIPE_API_KEY;
        // Start by finding existing customer or creating a new one if needed
        Customer customer = CustomerUtil.findOrCreateCustomer (orderDTO.getUserId ());

        // Create a PaymentIntent and send its client secret to the client
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder ()
                .setAmount ((long) (orderDTO.getTotal ().doubleValue () * 100L))
                .setCurrency ("usd")
                .setCustomer (customer.getId ())
                .setAutomaticPaymentMethods (PaymentIntentCreateParams.AutomaticPaymentMethods.builder ()
                        .setEnabled (true)
                        .build ()
                )
                .build ();

        try {
            PaymentIntent paymentIntent = PaymentIntent.create (params);
            Payment payment = new Payment ();
            paymentIntent.setStatus ("succeeded");
            if ("succeeded".equals (paymentIntent.getStatus ())) {
                payment.setStatus (PaymentStatus.CONFIRMED);
                payment.setPaymentId (paymentIntent.getId ());
                payment.setOrderId (orderDTO.getId ());
                paymentRepository.save (payment);
                orderDTO.setOrderStatus (OrderStatus.CONFIRMED);
                orderDTO.setPaymentStatus (PaymentStatus.CONFIRMED);
            } else {
                payment.setStatus (PaymentStatus.REJECTED);
                payment.setPaymentId (paymentIntent.getId ());
                payment.setOrderId (orderDTO.getId ());
                paymentRepository.save (payment);
                orderDTO.setOrderStatus (OrderStatus.PROCESSING);
                orderDTO.setPaymentStatus (PaymentStatus.REJECTED);
            }
            paymentPublisher.paymentConfirm (orderDTO);
            return paymentIntent.getStatus ();
        } catch (StripeException e) {
            throw e;
        }
    }

    @Override
    public String rollBack (OrderDto orderDTO) throws StripeException {
        Stripe.apiKey = STRIPE_API_KEY;
        Payment payment = paymentRepository.findByOrderId (orderDTO.getId ());

        PaymentIntent resource = PaymentIntent.retrieve (payment.getPaymentId ());

        PaymentIntentCancelParams params = PaymentIntentCancelParams.builder ().build ();

        PaymentIntent paymentIntent = resource.cancel (params);

        payment.setStatus (PaymentStatus.ROLLBACK);
        paymentRepository.save (payment);
        orderDTO.setOrderStatus (OrderStatus.ROLLBACK);
        orderDTO.setPaymentStatus (PaymentStatus.ROLLBACK);
         paymentPublisher.paymentCanceled (orderDTO);
        return paymentIntent.getStatus ();
    }



}
