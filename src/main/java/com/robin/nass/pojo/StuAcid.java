package com.robin.nass.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;

import java.util.Date;

/**
 * 学生核酸信息表
 */
@Data
public class StuAcid {
    private Long id;

    private Long fstudentid;

    @JsonFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss",timezone="GMT+8")
    private Date fsampletime;

    @JsonFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss",timezone="GMT+8")
    private Date ftesttime;

    private String ftestunit;

    private Long fresult;

    private String ftestaddress;

    private String memo;
}
