package com.robin.nass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.robin.nass.pojo.StuAccommodation;
import com.robin.nass.pojo.StuStudent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName StuAccommodationMapper
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/27 21:43
 */
@Mapper
public interface StuAccommodationMapper extends BaseMapper<StuAccommodation> {
    List<StuStudent> getNotHasDormStu();
}
