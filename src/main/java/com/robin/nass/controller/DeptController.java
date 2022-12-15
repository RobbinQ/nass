package com.robin.nass.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.robin.nass.common.httpUtils.ApiResult;
import com.robin.nass.common.httpUtils.ResponseStatus;
import com.robin.nass.pojo.SysDept;
import com.robin.nass.pojo.SysDeptTree;
import com.robin.nass.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DeptController
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/22 12:55
 */
@RestController
@RequestMapping("/dept")
@CrossOrigin
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
    @GetMapping("/editDept")
    public ApiResult editDeptById(@RequestParam(value = "id") Long id,@RequestParam String name){
        sysDeptService.update(new LambdaUpdateWrapper<SysDept>().eq(SysDept::getId,id).set(SysDept::getName,name));
        return new ApiResult(ResponseStatus.SUCCESS,"修改成功！",null);
    }

    @GetMapping("/deleteDept")
    public ApiResult deleteDeptById(@RequestParam Long id){
        sysDeptService.removeById(id);
        return new ApiResult(ResponseStatus.SUCCESS,"删除成功！",null);
    }
}
