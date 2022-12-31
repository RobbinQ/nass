package com.robin.nass.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.robin.nass.common.httpUtils.ApiResult;
import com.robin.nass.common.httpUtils.ResponseStatus;
import com.robin.nass.pojo.StuAntiepidemic;
import com.robin.nass.pojo.StuStudent;
import com.robin.nass.pojo.dto.StuAntDto;
import com.robin.nass.service.StuAntiepidemicService;
import com.robin.nass.service.StuStudentService;
import com.robin.nass.service.dicService.DicHealthgradeService;
import com.robin.nass.service.dicService.DicHealthstatusService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @ClassName StuAntiepidemicController
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/27 10:29
 */
@RestController
@RequestMapping("/stuAnt")
@CrossOrigin
public class StuAntiepidemicController {
    @Autowired
    StuAntiepidemicService antiepidemicService;

    @Autowired
    StuStudentService studentService;

    @Autowired
    DicHealthgradeService healthgradeService;

    @Autowired
    DicHealthstatusService healthstatusService;

    @GetMapping("/stuAntInfo")
    public ApiResult getStuAntInfo(@RequestParam int page,
                                   @RequestParam int pagesize,
                                   @RequestParam String stuName){
        Page<StuAntiepidemic> stuAntINfo = new Page(page, pagesize);

        List<StuAntiepidemic> antiepidemicList=new ArrayList<>();
        if (StringUtils.isNotEmpty(stuName)){
            LambdaQueryWrapper<StuStudent> wrapper = new LambdaQueryWrapper<>();
            wrapper.like(StuStudent::getFname,stuName);
            List<StuStudent> stuStudents = studentService.list(wrapper);
            if (stuStudents.size()==0){
                return new ApiResult(ResponseStatus.WRONG,"未查到该生信息！",null);
            }
            //从疫情表中返回学生疫情信息
            antiepidemicList = stuStudents.stream().map((stu) ->{
                Long id = stu.getId();
                LambdaQueryWrapper<StuAntiepidemic> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(StuAntiepidemic::getFstudentid,id);
                StuAntiepidemic antiepidemic = antiepidemicService.getOne(queryWrapper);
                return antiepidemic;
            }).filter(Objects::nonNull).collect(Collectors.toList());
        }

        antiepidemicService.page(stuAntINfo);

        if (antiepidemicList.size()!=0) {
            stuAntINfo.setRecords(antiepidemicList);
        }

        Page<StuAntDto> dtoPage = new Page<>();
        BeanUtils.copyProperties(stuAntINfo,dtoPage,"records");
        //dto映射
        List<StuAntDto> antDtos = stuAntINfo.getRecords().stream().map((re) -> {
            StuAntDto stuAntDto = new StuAntDto();
            BeanUtils.copyProperties(re, stuAntDto);
            stuAntDto.setHealthStatusName(healthstatusService.getById(re.getFstatusid()).getFname());
            stuAntDto.setHealthCodeName(healthgradeService.getById(re.getFhealthid()).getFname());
            stuAntDto.setName(studentService.getById(re.getFstudentid()).getFname());
            return stuAntDto;
        }).collect(Collectors.toList());

        dtoPage.setRecords(antDtos);
        return new ApiResult(ResponseStatus.SUCCESS,"查询成功！",dtoPage);
    }
}
