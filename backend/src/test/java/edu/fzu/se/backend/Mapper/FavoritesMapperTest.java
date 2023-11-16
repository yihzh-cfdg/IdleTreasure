package edu.fzu.se.backend.Mapper;
import edu.fzu.se.backend.bean.Favorites;
import edu.fzu.se.backend.bean.Goods;
import edu.fzu.se.backend.mapper.FavoritesMapper;
import edu.fzu.se.backend.mapper.GoodsMapper;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.annotation.Configuration;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FavoritesMapperTest {
    @Autowired
    private FavoritesMapper favoritesMapper;
    private GoodsMapper goodsMapper;
    @Test
    void selectAll() {
        List<Favorites> favorites = favoritesMapper.selectAll();
        assertNotNull(favorites);
        assertTrue(!favorites.isEmpty());
    }

    @Test
    void selectById() {
        Long favoriteId = 1000000001L;
        Favorites favorite = favoritesMapper.selectById(favoriteId);
        assertNotNull(favorite);
        assertEquals(favoriteId, favorite.getFavorite_ID());
    }

    @Test
    void selectByUserId() {
        Long userId = 100000001L;
        List<Favorites> favorites = favoritesMapper.selectByUserId(userId);
        assertNotNull(favorites);
    }

    @Test
    void selectByKeyword() {
        String keyword = "台";
        List<Favorites> favorites = favoritesMapper.selectByKeyword(keyword);
        assertNotNull(favorites);
        assertTrue(!favorites.isEmpty());

    }

    @Test
    void deleteById() {
        Long favoriteId = 1000000001L;
        int result = favoritesMapper.deleteById(favoriteId);
        assertEquals(1, result);
    }

    @Test
    void insertFavorite() {
        Favorites favorite = new Favorites();
        favorite.setFavorite_ID(1000000001L);
        favorite.setUser_ID(100000001L);
        favorite.setGoods_ID(1000000001L);
        favorite.setInsert_Time("2022-01-01 00:00:00");
        int result = favoritesMapper.insertFavorite(favorite);
        assertEquals(1, result);
    }

    @Test
    void updateById() {
        Favorites favorite = new Favorites();
        favorite.setFavorite_ID(1000000002L);
        favorite.setUser_ID(100000002L);
        favorite.setGoods_ID(1000000002L);
        favorite.setInsert_Time("2022-01-01 00:00:00");
        int result = favoritesMapper.updateById(favorite);
        assertEquals(1, result);
    }
}