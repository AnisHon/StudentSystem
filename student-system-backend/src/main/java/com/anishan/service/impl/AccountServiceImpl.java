package com.anishan.service.impl;

import com.anishan.entity.Account;
import com.anishan.entity.UserInfo;
import com.anishan.mapper.AccountMapper;
import com.anishan.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
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
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Resource
    AccountMapper accountMapper;
    @Override
    public UserInfo getUserInfoByUserId(String userId) {
        return accountMapper.selectUserInfoByUserId(userId);
    }
}
