package edu.fzu.se.backend.Mapper;
import edu.fzu.se.backend.bean.Goods;
import edu.fzu.se.backend.bean.FrontendGood;
import edu.fzu.se.backend.bean.Images;
import edu.fzu.se.backend.mapper.GoodsMapper;
import edu.fzu.se.backend.mapper.ImagesMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GoodsMapperTest {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private ImagesMapper imagesMapper;
    @Test
    void selectAll() {
        List<Goods> goodsList = goodsMapper.selectAll();
        assertNotNull(goodsList);
        assertTrue(!goodsList.isEmpty());
    }

    @Test
    void deleteById() {
        Long goodsId = 1000000014L;
        int result = goodsMapper.deleteById(goodsId);
        assertEquals(1, result);
    }

    @Test
    void selectImagesByGoodsId() {
        Long goodsId = 1000000001L;
        List<Images> images = goodsMapper.selectImagesByGoodsId(goodsId);
        assertNotNull(images);
        assertTrue(!images.isEmpty());

    }

    @Test
    void selectById() {
        Long goodsId = 1000000001L;
        Goods goods = goodsMapper.selectById(goodsId);
        assertNotNull(goods);
        assertEquals(goodsId, goods.getGoods_ID());
    }

    @Test
    void selectBySellerId() {
        Long sellerId = 100000001L;
        List<Goods> goodsList = goodsMapper.selectBySellerId(sellerId);
        assertNotNull(goodsList);
        assertTrue(!goodsList.isEmpty());
    }

    @Test
    void selectByPriceRange() {
        int minPrice = 10;
        int maxPrice = 100;
        List<Goods> goodsList = goodsMapper.selectByPriceRange(minPrice, maxPrice);
        assertNotNull(goodsList);
        assertTrue(!goodsList.isEmpty());
        for (Goods goods : goodsList) {
            assertTrue(goods.getGoods_Price() >= minPrice && goods.getGoods_Price() <= maxPrice);
        }
    }

    @Test
    void selectByKeyword() {
        String keyword = "红";
        List<Goods> goodsList = goodsMapper.selectByKeyword(keyword);
        assertNotNull(goodsList);
    }

    @Test
    void selectGoodsByUserName() {
        String userName = "小美";
        List<Goods> goodsList = goodsMapper.selectGoodsByUserName(userName);
        assertNotNull(goodsList);
    }

    @Test
    void insertGood() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Goods goods = new Goods();
        goods.setGoods_ID(1000000015L);
        goods.setGoods_Name("乒乓球");
        goods.setSeller_ID(100000005L);
        goods.setGoods_Price(250.00);
        goods.setGoods_Description("这是一个测试商品");
        goods.setClassification("交通工具");
        goods.setRelease_Time(sdf.parse("2023-11-11 00:00:00"));
        int result = goodsMapper.insertGood(goods);
        assertEquals(1, result);
    }

    @Test
    void updateById() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Goods goods = new Goods();
        goods.setGoods_ID(1000000004L);
        goods.setSeller_ID(100000004L);
        goods.setGoods_Name("测试商品");
        goods.setGoods_Price(100.00);
        goods.setGoods_Description("这是一个测试商品");
        goods.setClassification("文娱用品");
        goods.setRelease_Time(sdf.parse("2023-11-12 12:07:52"));

        int result = goodsMapper.updateById(goods);
        assertEquals(1, result);
    }

    @Test
    void getHomePageGoods() {
        List<FrontendGood> li = goodsMapper.getHomePageGoods();
        System.out.println(li);
    }
}