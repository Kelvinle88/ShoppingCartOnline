package osc.consumer;

import osc.events.OrderEvent;

public interface ProductConsumer {
    //void receiveMessageFromOrder(List <ProductDto> productDtos);
    public void processOrderEvent(OrderEvent orderEvent);
}
