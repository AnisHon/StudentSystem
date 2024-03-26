package com.anishan.service;

import com.anishan.entity.Account;
import com.anishan.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author anishan
 * @since 2024-03-23
 */
public interface AccountService extends IService<Account> {
    UserInfo getUserInfoByUserId(String userId);

}
