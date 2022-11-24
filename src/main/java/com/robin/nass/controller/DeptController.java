package com.robin.nass.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.robin.nass.common.httpUtils.ApiResult;
import com.robin.nass.common.httpUtils.ResponseStatus;
import com.robin.nass.pojo.SysDept;
import com.robin.nass.pojo.SysDeptTree;
import com.robin.nass.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DeptController
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/22 12:55
 */
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    SysDeptService sysDeptService;

    @GetMapping("/getInfo")
    public ApiResult getDeptInfo(){
        return new ApiResult(ResponseStatus.SUCCESS,"查询成功！",sysDeptService.list());
    }

    @GetMapping("/getDeptTree")
    public ApiResult getDeptTree(){
        return new ApiResult(ResponseStatus.SUCCESS,"查询成功！",sysDeptService.getDeptTree());
    }
}
