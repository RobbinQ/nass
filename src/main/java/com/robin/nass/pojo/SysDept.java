package com.robin.nass.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

/**
 * @ClassName SysDept
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/22 12:39
 */
@Data
public class SysDept {
    private Long id;
    private String code;
    private String name;
    private String memo;

    @TableLogic
    private Integer deleted;
}
