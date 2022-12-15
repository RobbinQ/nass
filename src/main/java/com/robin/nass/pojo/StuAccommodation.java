package com.robin.nass.pojo;

import lombok.Data;

@Data
//每个寝室
public class StuAccommodation {
    private Long id;

    private Long fdormitoryid;

    private Long fstudentid;

    private Integer fbed;

    private String fin;

    private String memo;
}