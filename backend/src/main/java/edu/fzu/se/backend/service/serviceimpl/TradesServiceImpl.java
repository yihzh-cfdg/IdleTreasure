package edu.fzu.se.backend.service.serviceimpl;

import edu.fzu.se.backend.bean.Trades;
import edu.fzu.se.backend.mapper.TradesMapper;
import edu.fzu.se.backend.service.TradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TradesServiceImpl implements TradesService {
    @Autowired
    TradesMapper tradesMapper;

    public void addTrade(Trades trades){

    }

}
