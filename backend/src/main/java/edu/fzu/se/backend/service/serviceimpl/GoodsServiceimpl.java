package edu.fzu.se.backend.service.serviceimpl;

import edu.fzu.se.backend.bean.Goods;
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
    public List<Goods> maingetgoods(){
        List<Goods> goods = goodsMapper.selectAll();
        int sum = 6; //主页要显示的商品数量
        Random rand = new Random();
        List<Goods> mainGoods = new ArrayList<>();
        while (sum != 0) {
            int randomIndex = rand.nextInt(goods.size());
            Goods randomGoods = goods.get(randomIndex);
            mainGoods.add(randomGoods);
            goods.remove(randomIndex);
            sum--;
        }
        return mainGoods;
    }
}
