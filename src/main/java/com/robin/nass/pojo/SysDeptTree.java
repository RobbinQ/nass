package com.robin.nass.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SysDeptTree
 * @Description 部门树
 * @Author Robin
 * @Date 2022/11/24 18:41
 */
@Data
public class SysDeptTree extends SysDept{
    private List<SysDeptTree> children;

    public SysDeptTree(Long id,String code,String name,String memo,Integer deleted) {
        this.setId(id);
        this.setCode(code);
        this.setName(name);
        this.setMemo(memo);
        this.setDeleted(deleted);
        this.setChildren(new ArrayList<>());
    }
}
