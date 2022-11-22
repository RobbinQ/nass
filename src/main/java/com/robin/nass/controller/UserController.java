package com.robin.nass.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.robin.nass.common.httpUtils.ApiResult;
import com.robin.nass.common.httpUtils.ResponseStatus;
import com.robin.nass.common.securityUtils.JwtUtil;
import com.robin.nass.pojo.SysUser;
import com.robin.nass.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/19 13:52
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    SysUserService sysUserService;

    /**
     * 登录接口
     * @param sysUser
     * @return
     */
    @PostMapping("/login")
    public ApiResult login(@RequestBody SysUser sysUser){
        SysUser user = sysUserService.getById(sysUser.getId());
        if (user == null){
            return new ApiResult(ResponseStatus.WRONG,"请先注册！",null);
        }
        if (!user.getPassword().equals(sysUser.getPassword())){
            return new ApiResult(ResponseStatus.WRONG,"密码错误！",null);
        }
        //登录成功，返回token
        String token =  JwtUtil.LoginToToken(sysUser.getUsername(),sysUser.getPassword());
        return new ApiResult(ResponseStatus.SUCCESS,"登录成功！",token);
    }

    @PostMapping("add")
    public ApiResult addUser (@RequestBody SysUser sysUser){
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername,sysUser.getUsername());
        SysUser hasOne = sysUserService.getOne(wrapper);
        if (hasOne == null){
            boolean save = sysUserService.save(sysUser);
            if (save){
                return new ApiResult(ResponseStatus.SUCCESS,"注册成功！",null);
            }
        }
        return new ApiResult(ResponseStatus.WRONG,"注册失败！",null);
    }
}
