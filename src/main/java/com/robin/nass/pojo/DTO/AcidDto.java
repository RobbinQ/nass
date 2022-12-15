package com.robin.nass.pojo.dto;

import com.robin.nass.pojo.StuAcid;
import lombok.Data;

/**
 * @ClassName AcidDto
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/26 18:32
 */
@Data
public class AcidDto extends StuAcid {
    private String stuName;
    private String resAcidName;
}
