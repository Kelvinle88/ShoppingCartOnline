package osc.utilities;

import osc.entity.Item;

import java.math.BigDecimal;
import java.util.List;

public class OrderUtilities {
    public static BigDecimal countTotalPrice(List <Item> cart){
        BigDecimal total = BigDecimal.ZERO;
        for(int i = 0; i < cart.size(); i++){
            total = total.add(cart.get(i).getSubTotal());
        }
        return total;
    }
    public static int countTotalQuantity(List <Item> cart){
        int total = 0;
        for(int i = 0; i < cart.size(); i++){
            total +=cart.get (i).getQuantity ();
            //total = total.add(cart.get(i).getSubTotal());
        }
        return total;
    }
}
