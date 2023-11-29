package edu.fzu.se.backend.service.serviceimpl;

import edu.fzu.se.backend.bean.Goods;
import edu.fzu.se.backend.bean.FrontendGood;
import edu.fzu.se.backend.bean.Images;
import edu.fzu.se.backend.mapper.GoodsMapper;
import edu.fzu.se.backend.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GoodsServiceimpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    //返回指定页面商品信息
    @Override
    public List<Goods> classgetgoods(String Classification){
        return goodsMapper.selectByClassification(Classification);
    }
    //返回主页商品信息
    @Override
    public List<FrontendGood> maingetgoods(){
        List<FrontendGood> goods = goodsMapper.getHomePageGoods();
        int sum = 6; //主页要显示的商品数量
        if(goods.size() < sum)
            sum = goods.size();
        Random rand = new Random();
        List<FrontendGood> mainGoods = new ArrayList<>();
        while (sum != 0) {
            int randomIndex = rand.nextInt(goods.size());
            FrontendGood randomGoods = goods.get(randomIndex);
            mainGoods.add(randomGoods);
            goods.remove(randomIndex);
            sum--;
        }
        return mainGoods;
    }

    @Override
    public List<Images> getImages(Long goodsId){
        return goodsMapper.getImages(goodsId);
    }

    @Override
    public List<FrontendGood> getSearchGoods(String value) {
        return goodsMapper.selectByKeyword(value);
    }
}
