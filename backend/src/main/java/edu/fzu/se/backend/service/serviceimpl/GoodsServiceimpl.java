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
    public List<Goods> classgetgoods(String classification){
        List<Goods> goods=goodsMapper.selectAll();
        List<Goods> matchingGoods = new ArrayList<>();
        for(Goods g : goods){
            if(classification.equals(g.getClassification())){
                matchingGoods.add(g);
            }
        }
        return matchingGoods;
    }
    //返回主页商品信息
    @Override
    public List<Goods> maingetgoods(){
        List<Goods> goods=goodsMapper.selectAll();
        List<Goods> mainGoods = new ArrayList<>();
        Random rand = new Random();
        int sum=5;//主页要显示的商品数量
        long randomLong = rand.nextLong(1000) + 1;
        while (sum!=0) {
            for (Goods g : goods) {
                if (g.getGoods_ID().equals(randomLong)) {
                    mainGoods.add(g);
                }
            }
            sum--;
            randomLong = rand.nextLong(1000) + 1;
        }
        return mainGoods;
    }
}
