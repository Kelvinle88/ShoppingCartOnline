package osc.consumer;

import osc.events.OrderEvent;

public interface ProductConsumer {
    public void processOrderEvent(OrderEvent orderEvent);
}
