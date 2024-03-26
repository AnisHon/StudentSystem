package com.anishan.controller;

import com.anishan.service.ScoreService;
import jakarta.annotation.Resource;
import jakarta.websocket.server.PathParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
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
public class ScoreController {
    @Resource
    private ScoreService scoreService;
    @ResponseBody
    @GetMapping(value = "/scores/{name}", produces = "application/json")
    public String getScores(@PathVariable String name) {
        return scoreService.getScores(name).toJson();
    }

    @ResponseBody
    @GetMapping(value = "/scores/", produces = "application/json")
    public String getScores() {
        return scoreService.getScores("").toJson();
    }

    @ResponseBody
    @GetMapping(value = "/my-score", produces = "application/json")
    public String getScore() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        System.out.println(principal.getAuthorities());

        return scoreService.getScoreJson(principal.getUsername());
    }

}
