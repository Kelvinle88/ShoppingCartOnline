package osc.orderservice.utilities;

import osc.orderservice.entity.Product;

import java.math.BigDecimal;

public class CartUtilities {
    public static BigDecimal getSubTotalForItem(Product product,int quantity){
        return (product.getProductPrice()).multiply(BigDecimal.valueOf(quantity));
    }
}
