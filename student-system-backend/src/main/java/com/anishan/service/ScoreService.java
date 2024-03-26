package com.anishan.service;

import com.anishan.entity.RestEntity;
import com.anishan.entity.Score;
import com.anishan.entity.StudentScore;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author anishan
 * @since 2024-03-23
 */
public interface ScoreService extends IService<Score> {

    RestEntity<List<StudentScore>> getScores(String name);

    String getScoreJson(String userId);
}
