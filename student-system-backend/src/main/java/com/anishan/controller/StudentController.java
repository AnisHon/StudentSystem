package com.anishan.controller;

import com.anishan.service.StudentService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author anishan
 * @since 2024-03-23
 */
@Controller
@RequestMapping("/api")
@RestController
public class StudentController {

    @Resource
    StudentService studentService;

    @GetMapping(value = "/students/{name}", produces = "application/json")
    @ResponseBody
    public String getStudents(@PathVariable String name) {
        return studentService.getStudentAsJson(name);
    }
    @GetMapping(value = "/students/", produces = "application/json")
    @ResponseBody
    public String getStudents() {
        return studentService.getStudentAsJson("");
    }



}
