package edu.fzu.se.backend.Mapper;
import edu.fzu.se.backend.bean.WxUser;
import edu.fzu.se.backend.mapper.WxUserMapper;
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

//@SpringJUnitConfig(locations = "classpath:spring-boot-test.xml")
@SpringBootTest
class WxUserMapperTest {

    @Autowired
    private WxUserMapper wxUserMapper;

    @Test
    void selectAll() {
        List<WxUser> users = wxUserMapper.selectAll();
        assertNotNull(users);
    }

    @Test
    void selectById() {
        Long userId = 100000001L;
        WxUser user = wxUserMapper.selectById(userId);
        assertNotNull(user);
        assertEquals(userId, user.getUser_ID());
    }

    @Test
    void selectByFzuKey() {
        String fzuKey = "102101519";
        WxUser user = wxUserMapper.selectByFzuKey(fzuKey);
        assertNotNull(user);
        assertEquals(fzuKey, user.getFZU_Key());
    }

    @Test
    void selectByKeyword() {
        String keyword = "小";
        List<WxUser> users = wxUserMapper.selectByKeyword(keyword);
        assertNotNull(users);
    }

    @Test
    void deleteById() {
        Long userId = 100000011L;
        int result = wxUserMapper.deleteById(userId);
        assertEquals(1, result);
    }

    @Test
    void insertUser() {
        WxUser user = new WxUser();
        user.setUser_Name("小易");
        user.setUser_Key("password8");
        user.setFZU_Key("102101526");
        user.setHead_Portrait("6");
        user.setDelivery_Address("福州大学1号楼909室");
        int result = wxUserMapper.insertUser(user);
        assertEquals(1, result);
    }

    @Test
    void updateById() {
        WxUser user = new WxUser();
        user.setUser_ID(100000001L);
        user.setUser_Name("小何");
        user.setUser_Key("password14");
        user.setHead_Portrait("8");
        user.setDelivery_Address("福州大学1号楼110室");
        int result = wxUserMapper.updateById(user);
        assertEquals(1, result);
    }
}
