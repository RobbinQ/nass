package com.robin.nass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.robin.nass.pojo.SysDept;
import com.robin.nass.pojo.SysDeptTree;

import java.util.List;

/**
 * @ClassName SysDeptService
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/22 12:43
 */

public interface SysDeptService extends IService<SysDept> {
    List<SysDeptTree> getDeptTree();
    void findTreeChildren(SysDeptTree tree,List<SysDept> depts);
}
