package com.robin.nass.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robin.nass.mapper.StuAccommodationMapper;
import com.robin.nass.pojo.StuAccommodation;
import com.robin.nass.pojo.dto.AccomDto;
import com.robin.nass.service.StuAccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName StuAccommodationServiceImpl
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/27 21:44
 */
@Service
public class StuAccommodationServiceImpl extends ServiceImpl<StuAccommodationMapper, StuAccommodation> implements StuAccommodationService {

}
