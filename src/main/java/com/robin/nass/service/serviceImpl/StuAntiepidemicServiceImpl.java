package com.robin.nass.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robin.nass.mapper.StuAntiepidemicMapper;
import com.robin.nass.pojo.StuAntiepidemic;
import com.robin.nass.service.StuAntiepidemicService;
import org.springframework.stereotype.Service;

/**
 * @ClassName StuAntiepidemicServiceImpl
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/25 11:07
 */
@Service
public class StuAntiepidemicServiceImpl extends ServiceImpl<StuAntiepidemicMapper, StuAntiepidemic>
        implements StuAntiepidemicService {
}
