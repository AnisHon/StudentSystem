package com.anishan.controller;

import com.anishan.entity.RestEntity;
import com.anishan.entity.UserInfo;
import com.anishan.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.anishan.entity.RestEntity.failure;

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
public class AccountController {
    @Resource
    AccountService accountService;
    @ResponseBody
    @GetMapping(value = "/me", produces = "application/json")
    public String getMe() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return RestEntity.failure(403, "fail").toJson();
        }
        UserInfo userInfoByUserId = accountService.getUserInfoByUserId(authentication.getName());
        if (userInfoByUserId == null) {
            return RestEntity.failure(403, "fail").toJson();
        }
        return RestEntity.success(userInfoByUserId, "success").toJson();
    }

}
