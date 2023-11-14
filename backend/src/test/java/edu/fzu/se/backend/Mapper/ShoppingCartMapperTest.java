package edu.fzu.se.backend.Mapper;
import edu.fzu.se.backend.bean.ShoppingCart;
import edu.fzu.se.backend.mapper.ShoppingCartMapper;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.annotation.Configuration;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ShoppingCartMapperTest {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Test
    void selectByUserIdAndGoodsId() {
        ShoppingCart result = shoppingCartMapper.selectByUserIdAndGoodsId(100000001L, 1000000001L);
        assertNotNull(result);
        assertEquals(100000001L, result.getUser_ID());
        assertEquals(1000000001L, result.getGoods_ID());
    }

    @Test
    void selectByUserId() {
        List<ShoppingCart> result = shoppingCartMapper.selectByUserId(100000004L);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        for (ShoppingCart cart : result) {
            assertEquals(100000004L, cart.getUser_ID());
        }
    }

    @Test
    void insertShoppingCart() {
        ShoppingCart newShoppingCart = new ShoppingCart();
        newShoppingCart.setUser_ID(100000005L);
        newShoppingCart.setGoods_ID(1000000005L);
        newShoppingCart.setCount(1);
        int result = shoppingCartMapper.insertShoppingCart(newShoppingCart);
        assertEquals(1, result);
    }

    @Test
    void updateShoppingCartCount() {
        ShoppingCart updatedShoppingCart = new ShoppingCart();
        updatedShoppingCart.setUser_ID(100000004L);
        updatedShoppingCart.setGoods_ID(1000000005L);
        updatedShoppingCart.setCount(2);
        int result = shoppingCartMapper.updateShoppingCartCount(updatedShoppingCart);
        assertEquals(1, result);
    }

    @Test
    void deleteShoppingCart() {
        int result = shoppingCartMapper.deleteShoppingCart(100000005L, 1000000005L);
        assertEquals(1, result);
    }

    @Test
    void clearShoppingCart() {
        int result = shoppingCartMapper.clearShoppingCart(100000005L);
        assertTrue(result >= 0);
    }
}