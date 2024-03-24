package com.anishan;

import com.anishan.entity.Account;
import com.anishan.mapper.AccountMapper;
import com.fasterxml.jackson.annotation.JacksonAnnotation;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class CreateAccountTest {
    @Resource
    BCryptPasswordEncoder encoder;
    @Resource
    AccountMapper mapper;

    @Test
    public void create() {
        Account account = new Account();
        account.setUserId("testRole");
        account.setPassword(encoder.encode("Han123456."));
        account.setRole("teacher");
        mapper.insert(account);
    }


}
