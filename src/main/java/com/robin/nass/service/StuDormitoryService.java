package com.robin.nass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.robin.nass.pojo.StuDormitory;
import com.robin.nass.pojo.StuDormitoryTree;

import java.util.List;

/**
 * @ClassName StuDormitoryService
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/27 21:48
 */
public interface StuDormitoryService extends IService<StuDormitory> {
    void findTreeChildren(StuDormitoryTree tree,List<StuDormitory> dormitories);
    List<StuDormitoryTree> getDormitoryTree();
}
