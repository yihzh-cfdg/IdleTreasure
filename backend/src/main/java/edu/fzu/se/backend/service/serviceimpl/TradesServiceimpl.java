package edu.fzu.se.backend.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.fzu.se.backend.bean.Goods;
import edu.fzu.se.backend.mapper.GoodsMapper;
import edu.fzu.se.backend.service.TradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TradesServiceimpl extends ServiceImpl<GoodsMapper, Goods> implements TradesService {
}
