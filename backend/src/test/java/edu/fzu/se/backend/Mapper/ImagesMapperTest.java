package edu.fzu.se.backend.Mapper;

import edu.fzu.se.backend.bean.Evaluations;
import edu.fzu.se.backend.bean.Images;
import edu.fzu.se.backend.mapper.ImagesMapper;
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
@SpringBootTest
class ImagesMapperTest {

    @Autowired
    private ImagesMapper imagesMapper;

    @Test

    void testSelectAll() {
        List<Images> images = imagesMapper.selectAll();
        assertNotNull(images);
        System.out.println(images);
        assertTrue(!images.isEmpty());
    }

    @Test

    void testSelectById() {
        Long imageId = 7L;
        Images images = imagesMapper.selectById(imageId);
        assertNotNull(images);
        assertEquals(imageId, images.getImage_ID());
    }

    @Test

    void InsertImage() {
        Images images = new Images();
        images.setImage_ID(6L);
        images.setImage_Name("用户头像1.jpg");
        images.setImage_Data("base128编码的图片数据");
        images.setImage_Class("平台");
        Long result = imagesMapper.insertImage(images);
        assertEquals(1, result);
    }

    @Test

    void testUpdateImageById() {
        Images images = new Images();
        images.setImage_ID(1L);
        images.setImage_Name("用户头像1.jpg");
        images.setImage_Data("base128编码的图片数据");
        images.setImage_Class("平台");
        int result = imagesMapper.updateImageById(images);
        assertEquals(1, result);
    }

    @Test
    void deleteById() {
        Long searchID = 9L;
        int result =imagesMapper.deleteById(searchID);
        assertEquals(1,result);
    }
}
