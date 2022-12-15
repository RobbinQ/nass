package com.robin.nass.pojo.dto;

import com.robin.nass.pojo.StuAntiepidemic;
import lombok.Data;

/**
 * @ClassName StuAntDto
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/27 10:25
 */
@Data
public class StuAntDto extends StuAntiepidemic {
    private String name;
    private String healthCodeName;
    private String healthStatusName;
}
