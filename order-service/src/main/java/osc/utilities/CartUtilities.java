package osc.utilities;

import osc.entity.Product;

import java.math.BigDecimal;

public class CartUtilities {
    public static BigDecimal getSubTotalForItem(Product product,int quantity){
        return (product.getProductPrice()).multiply(BigDecimal.valueOf(quantity));
    }
}
