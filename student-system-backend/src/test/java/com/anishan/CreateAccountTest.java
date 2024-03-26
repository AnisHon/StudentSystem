package com.anishan;

import com.anishan.entity.Account;
import com.anishan.mapper.AccountMapper;
import com.anishan.service.AccountService;
import com.anishan.service.ScoreService;
import com.anishan.service.StudentService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class CreateAccountTest {
    @Resource
    BCryptPasswordEncoder encoder;
    @Resource
    AccountMapper mapper;

    @Resource
    AccountService service;

    @Resource
    ScoreService scoreService;

    @Resource
    StudentService studentService;
    @Test
    public void create() {
        Account account = new Account();
        account.setUserId("testStu3");
        account.setPassword(encoder.encode("Han123456"));
        account.setRole("student");
        mapper.insert(account);
    }


    @Test
    public void selectScores() {
        System.out.println(studentService.getStudentAsJson(""));
    }
    @Test
    public void errorFix() {
        service.getUserInfoByUserId("testRole");
    }



}
