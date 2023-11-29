package edu.fzu.se.backend.service;

import edu.fzu.se.backend.bean.Goods;
import edu.fzu.se.backend.bean.FrontendGood;
import edu.fzu.se.backend.bean.Images;

import java.util.List;

public interface GoodsService {
    //返回指定页面商品信息
    List<Goods> classgetgoods(String Classification);
    //返回主页商品信息
    List<FrontendGood> maingetgoods();
    List<Images> getImages(Long goodsId);
    List<FrontendGood> getSearchGoods(String value);
}
