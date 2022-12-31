package com.robin.nass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.robin.nass.pojo.StuAcid;
import com.robin.nass.pojo.StuStudent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName StuAcidMapper
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/25 11:05
 */
@Mapper
public interface StuAcidMapper extends BaseMapper<StuAcid> {
    List<StuStudent> getNotAcidStu();
}
