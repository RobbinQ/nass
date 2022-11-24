package com.robin.nass.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.robin.nass.common.httpUtils.ApiResult;
import com.robin.nass.common.httpUtils.ResponseStatus;
import com.robin.nass.pojo.DTO.StuDTO;
import com.robin.nass.pojo.StuStudent;
import com.robin.nass.pojo.dictionaries.DicClassrole;
import com.robin.nass.pojo.dictionaries.DicNationality;
import com.robin.nass.pojo.dictionaries.DicPolitics;
import com.robin.nass.service.DicClassRoleService;
import com.robin.nass.service.DicNationalityService;
import com.robin.nass.service.DicPoliticsService;
import com.robin.nass.service.StuStudentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName StudentController
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/22 16:08
 */
@RestController
@RequestMapping("/stu")
public class StudentController {

    @Autowired
    StuStudentService stuStudentService;

    @Autowired
    DicPoliticsService dicPoliticsService;  //政治面貌

    @Autowired
    DicNationalityService dicNationalityService;    //民族

    @Autowired
    DicClassRoleService dicClassRoleService;    //班级角色

    /**
     * 添加学生
     * @param stuStudent
     * @return
     */
    @PostMapping("/addStu")
    public ApiResult addStu(@RequestBody StuStudent stuStudent){
        boolean save = stuStudentService.save(stuStudent);
        if (save){
            return new ApiResult(ResponseStatus.SUCCESS,"添加成功!",null);
        }else {
            return new ApiResult(ResponseStatus.WRONG,"添加失败!",null);
        }
    }

    /*
    注册所需的民族、政治面貌、班级角色信息
     */
    @GetMapping("/getInfoForStuRegister")
    public ApiResult getInfoForStuRegister(){
        HashMap<String, List<Object>> infoForRegister = new HashMap<>();

        List<DicClassrole> classRoleList = dicClassRoleService.list();
        List<DicNationality> nationalityList = dicNationalityService.list();
        List<DicPolitics> politicsList = dicPoliticsService.list();

        infoForRegister.put("classRoleList", Collections.singletonList(classRoleList));
        infoForRegister.put("nationalityList", Collections.singletonList(nationalityList));
        infoForRegister.put("politicsList", Collections.singletonList(politicsList));

        return new ApiResult(ResponseStatus.SUCCESS,"查询成功！",infoForRegister);
    }

    /**
     * 通过文件导入学生信息
     * @param studentFile
     * @return
     */
    @RequestMapping("/importStudentByFile")
    public ApiResult importStudentByFile(MultipartFile studentFile){
        Long stuCount = stuStudentService.importStu(studentFile);
        return new ApiResult(ResponseStatus.SUCCESS,"导入成功！",stuCount);
    }

    /**
     * 分页查询学生列表
     * @param page
     * @param pagesize
     * @param studentName
     * @return
     */
    @GetMapping("/stuInfo")
    public ApiResult getStuPageInfo(int page,int pagesize,String studentName){
        Page<StuStudent> studentInfo = new Page<>(page, pagesize);
        LambdaQueryWrapper<StuStudent> wrapper = new LambdaQueryWrapper<>();
        //当条件为真时添加
        wrapper.like(StringUtils.isNotEmpty(studentName),StuStudent::getFname,studentName);

        stuStudentService.page(studentInfo,wrapper);
        if (studentInfo.getRecords().size() == 0){
            return new ApiResult(ResponseStatus.WRONG,"没有查询到学生信息！",null);
        }
        //通过dto映射学生的民族、政治面貌、班级角色名称
        List<StuStudent> records = studentInfo.getRecords();

        List<StuDTO> dtoList = records.stream().map((stu) -> {
            StuDTO dto = new StuDTO();
            BeanUtils.copyProperties(stu, dto);

            DicPolitics politic = dicPoliticsService.getById(stu.getFpoliticsid());
            dto.setPoliticName(politic.getFname());

            DicNationality nationality = dicNationalityService.getById(stu.getFnationalityid());
            dto.setNationalityName(nationality.getFname());

            DicClassrole classrole = dicClassRoleService.getById(stu.getFclassroleid());
            dto.setClassRoleName(classrole.getFname());

            return dto;
        }).collect(Collectors.toList());

        Page<StuDTO> stuDTOPage = new Page<>();
        BeanUtils.copyProperties(studentInfo,stuDTOPage,"records");
        stuDTOPage.setRecords(dtoList);

        return new ApiResult(ResponseStatus.SUCCESS,"查询成功！",stuDTOPage);
    }
}
