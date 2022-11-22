package com.robin.nass.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robin.nass.mapper.SysDeptMapper;
import com.robin.nass.pojo.SysDept;
import com.robin.nass.service.SysDeptService;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysDeptServiceImpl
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/22 12:43
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
}
