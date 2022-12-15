package com.robin.nass.service.serviceImpl.dicServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robin.nass.mapper.DicHealthgradeMapper;
import com.robin.nass.pojo.dictionaries.DicHealthgrade;
import com.robin.nass.service.dicService.DicHealthgradeService;
import org.springframework.stereotype.Service;

/**
 * @ClassName DicHealthgradeServiceImpl
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/25 11:01
 */
@Service
public class DicHealthgradeServiceImpl extends ServiceImpl<DicHealthgradeMapper, DicHealthgrade> implements DicHealthgradeService {
}
