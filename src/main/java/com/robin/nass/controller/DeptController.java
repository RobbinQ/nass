package com.robin.nass.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.robin.nass.common.httpUtils.ApiResult;
import com.robin.nass.common.httpUtils.ResponseStatus;
import com.robin.nass.pojo.SysDept;
import com.robin.nass.pojo.SysDeptTree;
import com.robin.nass.pojo.SysUser;
import com.robin.nass.service.SysDeptService;
import com.robin.nass.service.SysUserService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    SysUserService userService;

    @GetMapping("/getInfo")
    public ApiResult getDeptInfo(){
        return new ApiResult(ResponseStatus.SUCCESS,"查询成功！",sysDeptService.list());
    }

    @GetMapping("/getDeptTree")
    public ApiResult getDeptTree(){
        return new ApiResult(ResponseStatus.SUCCESS,"查询成功！",sysDeptService.getDeptTree());
    }
    @PostMapping("/editDept")
    public ApiResult editDeptById(@RequestBody SysDept dept){
        sysDeptService.update(new LambdaUpdateWrapper<SysDept>().eq(SysDept::getId,dept.getId()).set(SysDept::getName,dept.getName()));
        return new ApiResult(ResponseStatus.SUCCESS,"修改成功！",null);
    }

    @GetMapping("/deleteDept")
    public ApiResult deleteDeptById(@RequestParam Long id){
        sysDeptService.removeById(id);
        return new ApiResult(ResponseStatus.SUCCESS,"删除成功！",null);
    }

    @GetMapping("/getUserByDeptId")
    public ApiResult getUserByDeptId(@RequestParam Long id){
        List<SysUser> userList = userService.list(new LambdaQueryWrapper<SysUser>().eq(SysUser::getDeptId, id));
        return new ApiResult(ResponseStatus.SUCCESS,"查找成功！",userList);
    }

    @GetMapping("/transferDept")
    public ApiResult userTransferDept(@RequestParam Long dId,@RequestParam Long uId){
        SysUser user = userService.getById(uId);
        user.setDeptId(dId);
        userService.updateById(user);
        return new ApiResult(ResponseStatus.SUCCESS,"调动成功！",null);
    }
}
