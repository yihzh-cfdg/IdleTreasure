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
    public String releaseGoods(Goods goods)
    {
        if(goodsMapper.insert(goods)>0)
            return "Success";
        else
            return "Fail";
    }

}
