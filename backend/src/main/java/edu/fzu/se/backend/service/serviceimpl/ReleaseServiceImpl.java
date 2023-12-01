package edu.fzu.se.backend.service.serviceimpl;

import edu.fzu.se.backend.bean.Goods;
import edu.fzu.se.backend.mapper.GoodsMapper;
import edu.fzu.se.backend.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReleaseServiceImpl implements ReleaseService {
    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public String releaseGoods(Goods goods) throws Exception
    {
        Long cnt = goodsMapper.insert(goods);
        if(cnt>0)
            return goods.getGoods_ID().toString();
        else
            throw new RuntimeException("商品发布失败");
    }

}
