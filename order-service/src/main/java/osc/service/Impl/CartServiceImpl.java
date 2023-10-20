package osc.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osc.client.ProductFeignClient;
import osc.dto.ProductDto;
import osc.entity.Item;
import osc.entity.Product;
import osc.mapper.ProductMapper;
import osc.redis.CartRedisRepository;
import osc.service.CartService;
import osc.utilities.CartUtilities;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private ProductFeignClient productClient;
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CartRedisRepository cartRedisRepository;

    @Override
    public void addItemToCart(String cartId,Long productId,Integer quantity) {
        ProductDto productDto = productClient.getProductByProductId(productId);
        Product product = productMapper.toEntity (productDto);
        Item item = new Item(quantity,product, CartUtilities.getSubTotalForItem(product,quantity));
        cartRedisRepository.addItemToCart(cartId, item);
    }

    @Override
    public List <Object> getCart(String cartId) {
        return (List<Object>)cartRedisRepository.getCart(cartId, Item.class);
    }

    @Override
    public void changeItemQuantity(String cartId, Long productId, Integer quantity) {
        List<Item> cart = (List)cartRedisRepository.getCart(cartId, Item.class);
        for(Item item : cart){
            //if((item.getProduct().getId()).equals(productId)){
            if((item.getProduct().getProductId ()).equals(productId)){
                cartRedisRepository.deleteItemFromCart(cartId, item);
                item.setQuantity(quantity);
                item.setSubTotal(CartUtilities.getSubTotalForItem(item.getProduct(),quantity));
                cartRedisRepository.addItemToCart(cartId, item);
            }
        }
    }
    @Override
    public void deleteItemFromCart(String cartId, Long productId) {
        List<Item> cart = (List) cartRedisRepository.getCart(cartId, Item.class);
        for(Item item : cart){
           // if((item.getProduct().getId()).equals(productId)){
            if((item.getProduct().getProductId ()).equals(productId)){
                cartRedisRepository.deleteItemFromCart(cartId, item);
            }
        }
    }
    @Override
    public boolean checkIfItemIsExist(String cartId, Long productId) {
        List<Item> cart = (List) cartRedisRepository.getCart(cartId, Item.class);
        for(Item item : cart){
           // if((item.getProduct().getId()).equals(productId)){
            if((item.getProduct().getProductId ()).equals(productId)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Item> getAllItemsFromCart(String cartId) {
        List<Item> items = (List)cartRedisRepository.getCart(cartId, Item.class);
        return items;
    }

    @Override
    public void deleteCart(String cartId) {
        cartRedisRepository.deleteCart(cartId);
    }

}
