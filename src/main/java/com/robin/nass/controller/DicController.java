package com.robin.nass.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.robin.nass.common.httpUtils.ApiResult;
import com.robin.nass.common.httpUtils.ResponseStatus;
import com.robin.nass.pojo.dictionaries.*;

import com.robin.nass.service.dicService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    DicHealthgradeService healthgradeService;
    //班级角色
    @GetMapping("/getClassrole")
    public ApiResult getClassRole(@RequestParam int page,
                                  @RequestParam int pagesize){
        Page<DicClassrole> page1 = new Page(page,pagesize);
        QueryWrapper<DicClassrole> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("fsortnumber");
        Page<DicClassrole> classrolePage = classRoleService.page(page1,queryWrapper);
        //排序
        List<DicClassrole> classroleSortList = classrolePage.getRecords().stream()
                .sorted(Comparator.comparing(DicClassrole::getFsortnumber))
                .collect(Collectors.toList());
        classrolePage.setRecords(classroleSortList);
        return new ApiResult(ResponseStatus.SUCCESS,"查找成功！",classrolePage);
    }

    @PostMapping("/editClassrole")
    public ApiResult editClassRole(@RequestBody DicClassrole classrole){
        if(classrole.getId()==null){
            return new ApiResult(ResponseStatus.SUCCESS,"添加成功！",classRoleService.save(classrole));
        }
        return new ApiResult(ResponseStatus.SUCCESS,"修改成功!",classRoleService.updateById(classrole));
    }

    @PostMapping("/deleteClassrole")
    public ApiResult deleteClassRole(@RequestBody DicClassrole classrole){
        return new ApiResult(ResponseStatus.SUCCESS,"删除成功！",classRoleService.removeById(classrole.getId()));
    }

    //民族
    @GetMapping("/getNationality")
    public ApiResult getNationalityList(@RequestParam int page,
                                        @RequestParam int pagesize){

        Page<DicNationality> page1 = new Page(page,pagesize);
        QueryWrapper<DicNationality> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("fsortnumber");
        Page<DicNationality> nationalityPage = nationalityService.page(page1,queryWrapper);
        //排序
        List<DicNationality> nationalityList = nationalityPage.getRecords().stream()
                .sorted(Comparator.comparing(DicNationality::getFsortnumber))
                .collect(Collectors.toList());
        nationalityPage.setRecords(nationalityList);
        return new ApiResult(ResponseStatus.SUCCESS,"查找成功！",nationalityPage);
    }

    @PostMapping("/editAndAddNationality")
    public ApiResult editAndAddNationality(@RequestBody DicNationality nationality){
        if(nationality.getId()==null){
            return new ApiResult(ResponseStatus.SUCCESS,"添加成功！",nationalityService.save(nationality));
        }
        return new ApiResult(ResponseStatus.SUCCESS,"修改成功!",nationalityService.updateById(nationality));
    }
    @PostMapping("/deleteNationality")
    public ApiResult deleteNationality(@RequestBody DicNationality nationality){
        return new ApiResult(ResponseStatus.SUCCESS,"删除成功！",nationalityService.removeById(nationality.getId()));
    }

    //政治面貌
    @GetMapping("/getPolitics")
    public ApiResult getPoliticsList(@RequestParam int page,
                                     @RequestParam int pagesize){

        Page<DicPolitics> page1 = new Page(page,pagesize);
        QueryWrapper<DicPolitics> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("fsortnumber");
        Page<DicPolitics> dicPoliticsPage = politicsService.page(page1,queryWrapper);
        //排序
        List<DicPolitics> politicsList = dicPoliticsPage.getRecords().stream()
                .sorted(Comparator.comparing(DicPolitics::getFsortnumber))
                .collect(Collectors.toList());
        dicPoliticsPage.setRecords(politicsList);
        return new ApiResult(ResponseStatus.SUCCESS,"查找成功！",dicPoliticsPage);
    }

    @PostMapping("/editAndAddPolitics")
    public ApiResult editAndAddPolitics(@RequestBody DicPolitics politics){
        if(politics.getId()==null){
            return new ApiResult(ResponseStatus.SUCCESS,"添加成功！",politicsService.save(politics));
        }
        return new ApiResult(ResponseStatus.SUCCESS,"修改成功!",politicsService.updateById(politics));
    }
    @PostMapping("/deletePolitics")
    public ApiResult deletePolitics(@RequestBody DicPolitics politics){
        return new ApiResult(ResponseStatus.SUCCESS,"删除成功！",politicsService.removeById(politics.getId()));
    }

    //健康码
    @GetMapping("/getHealthStatus")
    public ApiResult getHealthStatus(@RequestParam int page,
                                     @RequestParam int pagesize){

        Page<DicHealthstatus> page1 = new Page(page,pagesize);
        QueryWrapper<DicHealthstatus> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("fsortnumber");
        Page<DicHealthstatus> healthstatusPage = healthstatusService.page(page1,queryWrapper);
        //排序
        List<DicHealthstatus> healthstatuses = healthstatusPage.getRecords().stream()
                .sorted(Comparator.comparing(DicHealthstatus::getFsortnumber))
                .collect(Collectors.toList());
        healthstatusPage.setRecords(healthstatuses);
        return new ApiResult(ResponseStatus.SUCCESS,"查找成功！",healthstatusPage);
    }

    @PostMapping("/editAndAddHealthStatus")
    public ApiResult editAndAddHealthStatus(@RequestBody DicHealthstatus healthstatus){
        if(healthstatus.getId()==null){
            return new ApiResult(ResponseStatus.SUCCESS,"添加成功！",healthstatusService.save(healthstatus));
        }
        return new ApiResult(ResponseStatus.SUCCESS,"修改成功!",healthstatusService.updateById(healthstatus));
    }
    @PostMapping("/deleteHealthStatus")
    public ApiResult deleteHealthStatus(@RequestBody DicHealthstatus healthstatus){
        return new ApiResult(ResponseStatus.SUCCESS,"删除成功！",healthstatusService.removeById(healthstatus.getId()));
    }

    //健康状态
    @GetMapping("/getHealthGrade")
    public ApiResult getHealthGrade(@RequestParam int page,
                                    @RequestParam int pagesize){

        Page<DicHealthgrade> page1 = new Page(page,pagesize);
        QueryWrapper<DicHealthgrade> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("fsortnumber");
        Page<DicHealthgrade> healthgradePage = healthgradeService.page(page1,queryWrapper);
        //排序
        List<DicHealthgrade> healthgrades = healthgradePage.getRecords().stream()
                .sorted(Comparator.comparing(DicHealthgrade::getFsortnumber))
                .collect(Collectors.toList());
        healthgradePage.setRecords(healthgrades);
        return new ApiResult(ResponseStatus.SUCCESS,"查找成功！",healthgradePage);
    }

    @PostMapping("/editAndAddHealthGrade")
    public ApiResult editAndAddHealthGrade(@RequestBody DicHealthgrade healthgrade){
        if(healthgrade.getId()==null){
            return new ApiResult(ResponseStatus.SUCCESS,"添加成功！",healthgradeService.save(healthgrade));
        }
        return new ApiResult(ResponseStatus.SUCCESS,"修改成功!",healthgradeService.updateById(healthgrade));
    }

    @PostMapping("/deleteHealthGrade")
    public ApiResult deleteHealthGrade(@RequestBody DicHealthgrade healthgrade){
        return new ApiResult(ResponseStatus.SUCCESS,"删除成功！",healthgradeService.removeById(healthgrade.getId()));
    }
}
