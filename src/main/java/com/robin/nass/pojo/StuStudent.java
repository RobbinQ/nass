package com.robin.nass.pojo;

import lombok.Data;

/**
 * 学生表
 */
@Data
public class StuStudent {
    private Long id;

//    private Long fuserid;

    private Long fcode;

    private String fname;

    private String fgender;

    private String fmobile;

    private String fcontact;

    private String faddress;

    private String ffathername;

    private String ffathermobile;

    private String ffathermemo;

    private String fmothername;

    private String fmothermobile;

    private String fmothermemo;

    private String fidcode;

    private Long fpoliticsid;

    private Long fclassroleid;

    private Long fnationalityid;

    private String memo;
}