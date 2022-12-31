package com.robin.nass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.robin.nass.pojo.StuAcid;
import com.robin.nass.pojo.StuStudent;

import java.util.List;

/**
 * @ClassName StuAcidService
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/25 11:05
 */
public interface StuAcidService extends IService<StuAcid> {
    List<StuStudent> getNotAcidStu();
}
