package edu.fzu.se.backend.service.serviceimpl;

import edu.fzu.se.backend.service.WxUserService;
import org.springframework.stereotype.Service;
//整合后加入mapper等的引用

@Service
public class WxUserServiceimpl extends ServiceImpl<WxUserMapper,WxUser>implements WxUserService {
}
