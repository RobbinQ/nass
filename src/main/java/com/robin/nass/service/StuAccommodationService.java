package com.robin.nass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.robin.nass.pojo.StuAccommodation;
import com.robin.nass.pojo.StuStudent;
import com.robin.nass.pojo.dto.AccomDto;

import java.util.List;

/**
 * @ClassName StuAccommodationService
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/27 21:44
 */
public interface StuAccommodationService extends IService<StuAccommodation> {
    List<StuStudent> getNotHasDormStu();
}
