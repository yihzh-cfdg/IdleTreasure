package edu.fzu.se.backend.Mapper;
import edu.fzu.se.backend.bean.Goods_Images_Conns;
import edu.fzu.se.backend.mapper.Goods_Images_ConnsMapper;
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
class Goods_Images_ConnsMapperTest {
    @Autowired
    private Goods_Images_ConnsMapper goodsImagesConnsMapper;

    @Test
    void selectByGoodsAndImageIds() {
        Long goodsId = 1000000005L;
        Long imageId = 1L;
        Goods_Images_Conns result = goodsImagesConnsMapper.selectByGoodsAndImageIds(goodsId, imageId);
        assertEquals(goodsId, result.getGoods_ID());
        assertEquals(imageId, result.getImage_ID());
    }

    @Test
    void selectByGoodsId() {
        Long goodsId = 1000000005L;
        List<Goods_Images_Conns> resultList = goodsImagesConnsMapper.selectByGoodsId(goodsId);
        assertNotNull(resultList);
        assertTrue(!resultList.isEmpty());
    }

    @Test
    void insertConnection() {
        Goods_Images_Conns connection = new Goods_Images_Conns();
        connection.setGoods_ID(1000000004L);
        connection.setImage_ID(1L);
        int rowsAffected = goodsImagesConnsMapper.insertConnection(connection);
        assertTrue(rowsAffected > 0);
    }

    @Test
    void deleteConnection() {
        Long goodsId = 1000000004L;
        Long imageId = 1L;
        int rowsAffected = goodsImagesConnsMapper.deleteConnection(goodsId, imageId);
        assertTrue(rowsAffected > 0);
    }
}