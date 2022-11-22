package com.robin.nass.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.sun.istack.internal.NotNull;
import lombok.Data;

/**
 * @ClassName SysUser
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/19 13:10
 */
@Data
public class SysUser {

    @TableId(value = "id",type = IdType.ID_WORKER_STR)
    private Long id;

    private Long deptId;

    private String username;

    private String nickname;

    private String password;

    private String memo;

    @TableLogic//逻辑删除
    private Integer deleted;
}
