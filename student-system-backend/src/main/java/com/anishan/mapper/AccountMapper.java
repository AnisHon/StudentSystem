package com.anishan.mapper;

import com.anishan.entity.Account;
import com.anishan.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author anishan
 * @since 2024-03-23
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    @Select("""
    select account.id as id,
               account.user_id as userId,
               case
                   when account.role = 'teacher' then t.name
                   when account.role = 'student' then s.name
               end as name,
               account.role as role
        from account
            left join db02.student s on account.id = s.account_id
            left join db02.teacher t on account.id = t.account_id
        where account.user_id = #{userId}
        """)
    UserInfo selectUserInfoByUserId(String userId);

}
