package com.robin.nass.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class StuAntiepidemic {
    private Long id;

    private Long fstudentid;

    private String fincampus;

    private String flocation;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date fleavetime;

    private Long fhealthid;

    private String fhealthmemo;

    private Long fstatusid;

    private String fvaccincompleted;

    private String fvaccinmemo;

    private String fjourney;

    private String memo;
}
