package com.robin.nass.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.robin.nass.common.httpUtils.ApiResult;
import com.robin.nass.common.httpUtils.ResponseStatus;
import com.robin.nass.pojo.StuAccommodation;
import com.robin.nass.pojo.StuDormitory;
import com.robin.nass.pojo.StuStudent;
import com.robin.nass.pojo.dto.AccomDto;
import com.robin.nass.service.StuAccommodationService;
import com.robin.nass.service.StuDormitoryService;
import com.robin.nass.service.StuStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName AccommodationController
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/28 16:46
 */
@RestController
@CrossOrigin
@RequestMapping("/acc")
@Api(tags = "寝室相关接口")
public class AccommodationController {
    @Autowired
    StuAccommodationService accommodationService;

    @Autowired
    StuDormitoryService dormitoryService;

    @Autowired
    StuStudentService studentService;

    @ApiOperation("根据寝室id获取所有该寝室学生")
    @GetMapping("/getAccByDomid")
    public ApiResult getAccmAndStuByDomId(Long domId) {
        //根据寝室id获取所有该寝室学生
        LambdaQueryWrapper<StuAccommodation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StuAccommodation::getFdormitoryid,domId)
                .orderByAsc(StuAccommodation::getFbed);
        List<StuAccommodation> accomList = accommodationService.list(wrapper);

        //通过dto展现学生姓名，寝室名称：xx栋xx寝室
        List<AccomDto> accomDtoList = accomList.stream().map((acco) -> {
            AccomDto accomDto = new AccomDto();
            BeanUtils.copyProperties(acco, accomDto);
            String stuName = studentService.getById(acco.getFstudentid()).getFname();
            accomDto.setStuName(stuName);

            String dormName = dormitoryService.getById(acco.getFdormitoryid()).getFname();
            accomDto.setDormitoryName(dormName);

            return accomDto;
        }).collect(Collectors.toList());

        return new ApiResult(ResponseStatus.SUCCESS,"查询成功！",accomDtoList);
    }

    @ApiOperation("向寝室添加学生")
    @PostMapping("/addStuToAccm")
    public ApiResult addStuToAccm(@RequestBody StuAccommodation accommodation){
        Long fdormitoryId = accommodation.getFdormitoryid();
        //查看寝室容量
        StuDormitory dormitory = dormitoryService.getById(fdormitoryId);
        Integer fcapacity = dormitory.getFcapacity();
        if (fcapacity == 0){    //寝室满员
            return new ApiResult(ResponseStatus.WRONG,"寝室已满！",null);
        }
        accommodationService.save(accommodation);
        //更新楼栋-寝室表
        LambdaUpdateWrapper<StuDormitory> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(StuDormitory::getId,dormitory.getId())
                .set(StuDormitory::getFcapacity,dormitory.getFcapacity()-1);
        dormitoryService.update(wrapper);

        return new ApiResult(ResponseStatus.SUCCESS,"添加成功！",null);
    }

    @ApiOperation("部门树")
    @GetMapping("/dormTree")
    public ApiResult getDormTree(){
        return new ApiResult(ResponseStatus.SUCCESS,"查找成功！",dormitoryService.getDormitoryTree());
    }

    @ApiOperation("将学生移出寝室")
    @GetMapping("/removeStu")
    public ApiResult removeStuByStuId(Long id){
        LambdaQueryWrapper<StuAccommodation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StuAccommodation::getFstudentid,id);
        //学生在寝室信息
        StuAccommodation accommodation = accommodationService.getOne(wrapper);
        accommodationService.remove(wrapper);

        //更新楼栋-寝室表
        StuDormitory dormitory = dormitoryService.getById(accommodation.getFdormitoryid());
        LambdaUpdateWrapper<StuDormitory> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(StuDormitory::getFcapacity,dormitory.getFcapacity()+1)
                        .eq(StuDormitory::getId,dormitory.getId());
        dormitoryService.update(updateWrapper);

        return new ApiResult(ResponseStatus.SUCCESS,"删除成功！",null);
    }

    @GetMapping("/getStuAcc")
    public ApiResult getStuAccByStuName(String stuName){
        LambdaQueryWrapper<StuStudent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StuStudent::getFname,stuName);
        StuStudent student = studentService.getOne(wrapper);
        if (student == null){
            return new ApiResult(ResponseStatus.WRONG,"查找失败！",null);
        }
        Long stuId = student.getId();
        StuAccommodation accommodation = accommodationService.getOne(new LambdaQueryWrapper<StuAccommodation>()
                .eq(StuAccommodation::getFstudentid, stuId));
        if (accommodation == null){
            return new ApiResult(ResponseStatus.WRONG,"无在寝信息",null);
        }
        AccomDto accomDto = new AccomDto();
        BeanUtils.copyProperties(accommodation,accomDto);
        accomDto.setDormitoryName(dormitoryService.getById(accommodation.getFdormitoryid()).getFname());
        accomDto.setStuName(stuName);

        return new ApiResult(ResponseStatus.SUCCESS,"查找成功！",accomDto);
    }

    @GetMapping("/notHasDormStu")
    public ApiResult getNotHasDormStu(){
        return new ApiResult(ResponseStatus.SUCCESS,"查询成功！",accommodationService.getNotHasDormStu());
    }

    @PostMapping("/updateStu")
    public ApiResult updateStu(@RequestBody StuAccommodation accommodation){
        LambdaUpdateWrapper<StuAccommodation> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(StuAccommodation::getFstudentid,accommodation.getFstudentid())
                .set(StuAccommodation::getFbed,accommodation.getFbed())
                .set(StuAccommodation::getFin,accommodation.getFin());

        accommodationService.update(wrapper);

        return new ApiResult(ResponseStatus.SUCCESS,"修改成功！",null);
    }
}
