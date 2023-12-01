package edu.fzu.se.backend.service.serviceimpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.fzu.se.backend.bean.History;
import edu.fzu.se.backend.bean.WxUser;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.fzu.se.backend.mapper.*;
import org.springframework.stereotype.Service;
import edu.fzu.se.backend.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxUserServiceimpl implements  WxUserService {
    @Autowired
    private  WxUserMapper wxUserMapper;
    @Autowired
    private FavoritesMapper favoritesMapper;
    @Autowired
    private HistoryMapper historyMapper;
    @Autowired
    private TradesMapper tradesMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    //登录
    public Long loginService(String name,String password){
        String md5PW = DigestUtils.md5DigestAsHex(password.getBytes());
        WxUser queryUser = wxUserMapper.selectByUserName(name);
        if(md5PW.equals(queryUser.getUser_Key()))
            return queryUser.getUser_ID();
        else
            throw new RuntimeException("用户名或密码错误");
    }
    //注册
    @Override
    public boolean registerService(WxUser wxUser) throws RuntimeException{
        //查询用户
        WxUser one = wxUserMapper.selectByUserName(wxUser.getUser_Name());
        if(one != null){
            throw new RuntimeException("用户名被占用!");
        }
        //密码加密
        wxUser.setUser_Key(DigestUtils.md5DigestAsHex(wxUser.getUser_Key().getBytes()));
        //存到数据库
        return wxUserMapper.insertUser(wxUser) > 0;
    }
    //检查用户名是否重复
    @Override
    public boolean isUsernameExists(String name) {
        List<WxUser> users = wxUserMapper.selectAll();
        for (WxUser u : users) {
            if (name.equals(u.getUser_Name())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Map<String, String> getById(Long id) {
        WxUser u = wxUserMapper.selectById(id);
        Map<String, String> result = new HashMap<>();
        result.put("username", u.getUser_Name());
        result.put("userID", u.getFZU_Key());
        result.put("header", u.getHead_Portrait());
        result.put("fzukey", u.getFZU_Key());
        result.put("fans", u.getBeliked_Count().toString());
        result.put("follow", u.getLike_Count().toString());
        result.put("collect", favoritesMapper.countFavoriteByUserId(id).toString());
        result.put("history", historyMapper.selectItemCount().toString());
        Double amount = tradesMapper.selectTotalAmountBySellerId(id);
        if (amount == null)
            amount = 0.0;
        result.put("profile", amount.toString());
        result.put("publish", goodsMapper.countGoodsBySellerId(id).toString());
        result.put("sell", tradesMapper.selectTotalCountBySellerId(id).toString());
        result.put("buy", tradesMapper.selectTotalCountByBuyerId(id).toString());
        return result;
    }

    //修改用户名
    @Override
    public boolean updateUsername(Long id, String username) {
        return wxUserMapper.updateNameById(id, username) > 0;
    }

    public Map<String, String> getAddress(Long id){
        WxUser u = wxUserMapper.selectById(id);
        Map<String, String> result = new HashMap<>();
        result.put("delivery", u.getDelivery_Address());
        result.put("shipping", u.getShipping_Address());
        result.put("recipient", u.getRecipient());
        result.put("phone", u.getPhone());
        return result;
    }

    @Override
    public boolean updateReceiver(Long id, String receiver) {
        return wxUserMapper.updateRecipientById(id, receiver) > 0;
    }

    @Override
    public boolean updatePhone(Long id, String phone) {
        return wxUserMapper.updatePhoneById(id, phone) > 0;
    }

    @Override
    public boolean updateAddress(Long id, String address) {
        return wxUserMapper.updateAddressById(id, address) > 0;
    }
}
