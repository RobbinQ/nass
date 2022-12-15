package com.robin.nass.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName StuDormitoryTree
 * @Description TODO
 * @Author Robin
 * @Date 2022/12/13 0:03
 */
@Data
@NoArgsConstructor
public class StuDormitoryTree extends StuDormitory{
    private List<StuDormitoryTree> children;
}
