package com.robin.nass.service.serviceImpl.dicServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robin.nass.mapper.DicHealthstatusMapper;
import com.robin.nass.pojo.dictionaries.DicHealthstatus;
import com.robin.nass.service.dicService.DicHealthstatusService;
import org.springframework.stereotype.Service;

/**
 * @ClassName DicHealthstatusServiceImpl
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/25 11:04
 */
@Service
public class DicHealthstatusServiceImpl extends ServiceImpl<DicHealthstatusMapper, DicHealthstatus> implements DicHealthstatusService {
}
