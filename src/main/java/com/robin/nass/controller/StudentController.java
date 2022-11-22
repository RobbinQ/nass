package com.robin.nass.controller;

import com.robin.nass.common.httpUtils.ApiResult;
import com.robin.nass.common.httpUtils.ResponseStatus;
import com.robin.nass.pojo.StuStudent;
import com.robin.nass.service.StuStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

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

    /**
     * 添加学生
     * @param stuStudent
     * @return
     */
    @PostMapping("addStu")
    public ApiResult addStu(@RequestBody StuStudent stuStudent){
        boolean save = stuStudentService.save(stuStudent);
        if (save){
            return new ApiResult(ResponseStatus.SUCCESS,"添加成功!",null);
        }else {
            return new ApiResult(ResponseStatus.WRONG,"添加失败!",null);
        }
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
}
