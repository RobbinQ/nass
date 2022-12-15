package com.robin.nass.service.serviceImpl.dicServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robin.nass.mapper.StuDormitoryMapper;
import com.robin.nass.pojo.StuDormitory;
import com.robin.nass.pojo.StuDormitoryTree;
import com.robin.nass.service.StuDormitoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName StuDormitoryServiceImpl
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/27 21:49
 */
@Service
public class StuDormitoryServiceImpl extends ServiceImpl<StuDormitoryMapper, StuDormitory> implements StuDormitoryService {
    @Override
    public void findTreeChildren(StuDormitoryTree tree, List<StuDormitory> dormitories) {
        String code = tree.getFcode();
        int nowLen = code.length();
        for (StuDormitory dormitory : dormitories) {
            String fcode = dormitory.getFcode();
            int flen = fcode.length();
            if(flen == nowLen+2){
                String substring = fcode.substring(0, nowLen);
                if (substring.equals(code)){
                    StuDormitoryTree child = new StuDormitoryTree();
                    BeanUtils.copyProperties(dormitory,child);
                    child.setChildren(new ArrayList<>());
                    List<StuDormitoryTree> children = tree.getChildren();
                    children.add(child);
                    tree.setChildren(children);
                    findTreeChildren(child,dormitories);
                }
            }
        }
    }

    @Override
    public List<StuDormitoryTree> getDormitoryTree() {
        List<StuDormitory> dormitoryList = this.list();
        //返回根节点
        List<StuDormitoryTree> roots = dormitoryList.stream()
                .filter((dor) -> dor.getFcode().length() == 2)
                .map((dor)->{
                    StuDormitoryTree dormitoryTree = new StuDormitoryTree();
                    BeanUtils.copyProperties(dor,dormitoryTree);
                    dormitoryTree.setChildren(new ArrayList<>());
                    return dormitoryTree;
                })
                .collect(Collectors.toList());

        ArrayList<StuDormitoryTree> dormitoryTrees = new ArrayList<>();
        for (StuDormitoryTree root : roots) {
            findTreeChildren(root,dormitoryList);
            dormitoryTrees.add(root);
        }
        return dormitoryTrees;
    }
}
