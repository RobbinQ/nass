package com.robin.nass.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robin.nass.mapper.StuAcidMapper;
import com.robin.nass.pojo.StuAcid;
import com.robin.nass.pojo.StuStudent;
import com.robin.nass.service.StuAcidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName StuAcidServiceImpl
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/25 11:06
 */
@Service
public class StuAcidServiceImpl extends ServiceImpl<StuAcidMapper, StuAcid> implements StuAcidService {
    @Autowired
    StuAcidMapper acidMapper;
    @Override
    public List<StuStudent> getNotAcidStu() {
        return acidMapper.getNotAcidStu();
    }
}
