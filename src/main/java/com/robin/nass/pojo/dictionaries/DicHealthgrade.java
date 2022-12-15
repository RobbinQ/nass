package com.robin.nass.pojo.dictionaries;

import lombok.Data;

/**
 * 健康等级阴性、阳性
 */
@Data
public class DicHealthgrade {
    private Long id;

    private String fcode;

    private String fname;

    private Integer fsortnumber;

    private String memo;

}