package com.robin.nass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.robin.nass.pojo.StuStudent;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName StuStudentService
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/22 15:59
 */
public interface StuStudentService extends IService<StuStudent> {

    Long importStu(MultipartFile file);
}
