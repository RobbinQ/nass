package com.robin.nass.controller;

import com.robin.nass.common.httpUtils.ApiResult;
import com.robin.nass.common.httpUtils.ResponseStatus;
import com.robin.nass.pojo.dictionaries.DicClassrole;

import com.robin.nass.service.dicService.DicClassRoleService;
import com.robin.nass.service.dicService.DicHealthstatusService;
import com.robin.nass.service.dicService.DicNationalityService;
import com.robin.nass.service.dicService.DicPoliticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DicController
 * @Description TODO
 * @Author Robin
 * @Date 2022/12/13 15:58
 */
@RestController
@RequestMapping("/dic")
@CrossOrigin
public class DicController {
    @Autowired
    DicClassRoleService classRoleService;

    @Autowired
    DicPoliticsService politicsService;

    @Autowired
    DicNationalityService nationalityService;

    @Autowired
    DicHealthstatusService healthstatusService;

    //班级角色
    @GetMapping("/getClassrole")
    public ApiResult getClassRole(){
        return new ApiResult(ResponseStatus.SUCCESS,"查找成功！",classRoleService.list());
    }

    @PostMapping("/addClassrole")
    public ApiResult addClassRole(@RequestBody DicClassrole classrole){
        return new ApiResult(ResponseStatus.SUCCESS,"添加成功！",classRoleService.save(classrole));
    }

    @PostMapping("/editClassrole")
    public ApiResult editClassRole(@RequestBody DicClassrole classrole){
        return new ApiResult(ResponseStatus.SUCCESS,"修改成功!",classRoleService.updateById(classrole));
    }

    @DeleteMapping("/deleteClassrole")
    public ApiResult deleteClassRole(@RequestBody DicClassrole classrole){
        return new ApiResult(ResponseStatus.SUCCESS,"删除成功！",classRoleService.removeById(classrole.getId()));
    }
}
