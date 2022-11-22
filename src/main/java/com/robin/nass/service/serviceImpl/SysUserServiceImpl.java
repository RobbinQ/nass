package com.robin.nass.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robin.nass.mapper.SysUserMapper;
import com.robin.nass.pojo.SysUser;
import com.robin.nass.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @ClassName SysUserServiceImpl
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/19 13:20
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}
