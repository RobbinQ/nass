package com.robin.nass.pojo.dictionaries;

import lombok.Data;

/**
 * 健康码状态
 */
@Data
public class DicHealthstatus {
    private Long id;

    private String fcode;

    private String fname;

    private Integer fsortnumber;

    private String memo;
}