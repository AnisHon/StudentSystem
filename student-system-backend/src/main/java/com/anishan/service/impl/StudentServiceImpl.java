package com.anishan.service.impl;

import com.anishan.entity.Student;
import com.anishan.mapper.StudentMapper;
import com.anishan.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author anishan
 * @since 2024-03-23
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
