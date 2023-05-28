package com.robin.nass.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
//每个寝室
public class StuAccommodation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long fdormitoryid;

    private Long fstudentid;

    private Integer fbed;

    private String fin;

    private String memo;
}
