package com.robin.nass.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robin.nass.mapper.SysDeptMapper;
import com.robin.nass.pojo.SysDept;
import com.robin.nass.pojo.SysDeptTree;
import com.robin.nass.service.SysDeptService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SysDeptServiceImpl
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/22 12:43
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
    /**
     * 部门树
     * @return
     */
    @Override
    public List<SysDeptTree> getDeptTree() {
        List<SysDept> list = this.list();
        //根节点列表
        List<SysDept> lsTop = list.stream().filter(dept ->dept.getCode().length()==2)
                                            .collect(Collectors.toList());

//        List<SysDeptTree> deptTrees = lsTop.stream().map((dept) -> {
//            SysDeptTree aTree = new SysDeptTree(dept.getId(), dept.getCode(), dept.getName(), dept.getMemo(), dept.getDeleted());
//            return aTree;
//        }).collect(Collectors.toList());

        ArrayList<SysDeptTree> trees = new ArrayList<>();
        for (SysDept dept : lsTop) {
            SysDeptTree tree = new SysDeptTree(dept.getId(), dept.getCode(), dept.getName(), dept.getMemo(), dept.getDeleted());
            findTreeChildren(tree,list);
            trees.add(tree);
        }
        return trees;
    }

    /*
    递归查找子树
     */
    @Override
    public void findTreeChildren(SysDeptTree tree, List<SysDept> depts) {
        String codeNow = tree.getCode();
        int lenNow = codeNow.length();
        for (SysDept dept : depts) {
            SysDeptTree child;
            String code = dept.getCode();
            int len = code.length();
            if (len == (lenNow+2)){
                String codePrefix = code.substring(0, lenNow);
                if (codePrefix.equals(codeNow)){
                    child = new SysDeptTree(dept.getId(),dept.getCode(),dept.getName(),dept.getMemo(),dept.getDeleted());
                    tree.getChildren().add(child);
                    findTreeChildren(child,depts);
                }
            }
        }
    }
}
