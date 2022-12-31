package com.robin.nass.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robin.nass.mapper.StuStudentMapper;
import com.robin.nass.pojo.StuStudent;
import com.robin.nass.service.StuStudentService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @ClassName StuStudentServiceImpl
 * @Description TODO
 * @Author Robin
 * @Date 2022/11/22 16:00
 */
@Service
public class StuStudentServiceImpl extends ServiceImpl<StuStudentMapper, StuStudent>
        implements StuStudentService {

    @Override
    public Long importStu(MultipartFile file) {
        long res = 0;
        ArrayList<StuStudent> students = new ArrayList<>();

        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf(".") + 1);

        try {
            InputStream inputStream = file.getInputStream();
            Workbook wb = null;
            if (suffix.equals("xlsx")){
                wb = new XSSFWorkbook(inputStream);
            }else {
                wb = new HSSFWorkbook(inputStream);
            }
            Sheet sheet = wb.getSheetAt(0);

            if (null != sheet){
                for (int line = 2; line <=sheet.getLastRowNum() ; line++) {
                    StuStudent student = new StuStudent();
                    Row row = sheet.getRow(line);
                    if (null == row){
                        return new Long(0);
                    }
                    /*
                    封装学生信息
                     */
                    student.setFcode(Long.valueOf(row.getCell(0).getStringCellValue()));
                    student.setFname(row.getCell(1).getStringCellValue());
                    student.setFgender((row.getCell(2).getStringCellValue()));
                    student.setFmobile((row.getCell(3).getStringCellValue()));
                    student.setFcontact((row.getCell(4).getStringCellValue()));
                    student.setFaddress((row.getCell(5).getStringCellValue()));
                    student.setFfathername((row.getCell(6).getStringCellValue()));
                    student.setFfathermobile((row.getCell(7).getStringCellValue()));
                    student.setFfathermemo((row.getCell(7).getStringCellValue()));
                    student.setFmothername((row.getCell(8).getStringCellValue()));
                    student.setFmothermobile((row.getCell(9).getStringCellValue()));
                    student.setFmothermemo((row.getCell(10).getStringCellValue()));
                    student.setFidcode((row.getCell(11).getStringCellValue()));
                    student.setFpoliticsid(Long.valueOf((row.getCell(12).getStringCellValue())));
                    student.setFclassroleid(Long.valueOf((row.getCell(13).getStringCellValue())));
                    student.setFnationalityid(Long.valueOf((row.getCell(14).getStringCellValue())));
                    student.setMemo((row.getCell(15).getStringCellValue()));

                    students.add(student);
                }

                /*
                依据学号判断是否存在该学生
                 */
                res = students.stream()
                        .map((stu) -> {
                            LambdaQueryWrapper<StuStudent> wrapper = new LambdaQueryWrapper<>();
                            wrapper.eq(StuStudent::getFcode, stu.getFcode());
                            if (this.getOne(wrapper) == null) {
                                this.save(stu);
                            } else {
                                this.update(wrapper);
                            }
                            return stu;
                        }).count();
            }
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return new Long(0);
        }
    }
}
