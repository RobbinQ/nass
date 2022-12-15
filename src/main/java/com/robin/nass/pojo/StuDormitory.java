package com.robin.nass.pojo;

import lombok.Data;

@Data
//楼栋-宿舍层级
public class StuDormitory {
    private Long id;

    private String fcode;

    private String fname;

    private Integer fcapacity;  //寝室容量

    private String memo;
}