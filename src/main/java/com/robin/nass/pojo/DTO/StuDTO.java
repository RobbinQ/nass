package com.robin.nass.pojo.dto;

import com.robin.nass.pojo.StuStudent;
import lombok.Data;

/**
 * @ClassName StuDTO
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/24 17:23
 */
@Data
public class StuDto extends StuStudent {

    private String classRoleName;

    private String nationalityName;

    private String politicName;
}
