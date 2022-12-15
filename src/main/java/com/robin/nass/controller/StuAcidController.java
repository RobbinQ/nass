package com.robin.nass.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.robin.nass.common.httpUtils.ApiResult;
import com.robin.nass.common.httpUtils.ResponseStatus;
import com.robin.nass.pojo.StuAcid;
import com.robin.nass.pojo.StuStudent;
import com.robin.nass.pojo.dictionaries.DicHealthstatus;
import com.robin.nass.pojo.dto.AcidDto;
import com.robin.nass.service.StuAcidService;
import com.robin.nass.service.StuStudentService;
import com.robin.nass.service.dicService.DicHealthgradeService;
import com.robin.nass.service.dicService.DicHealthstatusService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName StuAcidController
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/25 11:20
 */
@RestController
@RequestMapping("/stuacid")
@CrossOrigin
public class StuAcidController {
    @Autowired
    StuAcidService stuAcidService;

    @Autowired
    StuStudentService stuStudentService;

    @Autowired
    DicHealthgradeService dicHealthgradeService;
    /**
     * 获取学生核酸信息表、按名字查询
     * @param page
     * @param pagesize
     * @param stuName
     * @return
     */
    @GetMapping("/getInfo")
    public ApiResult getStuAcidInfo(@RequestParam int page,
                                    @RequestParam int pagesize,
                                    @RequestParam String stuName) {
        Page<StuAcid> stuAcidPage = new Page(page,pagesize);

        List<StuAcid> stuAcidList = null;
        if (StringUtils.isNotEmpty(stuName)){
            //依据学生姓名查询
            LambdaQueryWrapper<StuStudent> stuIdWrapper = new LambdaQueryWrapper<>();
            stuIdWrapper.like(StringUtils.isNotEmpty(stuName),StuStudent::getFname,stuName);
            //学生姓名可能重复,返回list
            List<StuStudent> studentList = stuStudentService.list(stuIdWrapper);
            List<Long> ids = studentList.stream().map(stu -> stu.getId()).collect(Collectors.toList());

            stuAcidList = ids.stream().map(id -> stuAcidService
                            .getOne(new LambdaQueryWrapper<StuAcid>()
                                    .eq(StuAcid::getFstudentid,id)))
                    .collect(Collectors.toList());
            if (stuAcidList.size()==0){
                return new ApiResult(ResponseStatus.WRONG,"没有该生信息！",null);
            }
        }

        stuAcidService.page(stuAcidPage);

        if (stuAcidList != null){
            stuAcidPage.setRecords(stuAcidList);
        }

        List<StuAcid> acidRecords = stuAcidPage.getRecords();
        //映射学生姓名，检测结果
        List<AcidDto> acidDtoList = acidRecords.stream().map((re) -> {
            AcidDto acidDto = new AcidDto();
            BeanUtils.copyProperties(re, acidDto);
            acidDto.setStuName(stuStudentService.getById(re.getFstudentid()).getFname());
            acidDto.setResAcidName(dicHealthgradeService.getById(re.getFresult()).getFname());

            return acidDto;
        }).collect(Collectors.toList());

        Page<AcidDto> acidDtoPage = new Page<>();
        BeanUtils.copyProperties(stuAcidPage,acidDtoPage,"records");
        acidDtoPage.setRecords(acidDtoList);

        return new ApiResult(ResponseStatus.SUCCESS,"查询成功！",acidDtoPage);
    }
}
