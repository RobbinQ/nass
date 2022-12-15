package com.robin.nass.pojo.dto;

import com.robin.nass.pojo.StuAccommodation;
import lombok.Data;

/**
 * @ClassName AccommodationDto
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/27 21:37
 */
@Data
public class AccomDto extends StuAccommodation {
    //寝室成员名字
    private String stuName;

    //寝室位置名称
    private String dormitoryName;
}
