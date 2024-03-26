package com.anishan.service.impl;

import com.alibaba.fastjson2.JSON;
import com.anishan.entity.RestEntity;
import com.anishan.entity.Score;
import com.anishan.entity.StudentScore;
import com.anishan.mapper.ScoreMapper;
import com.anishan.mapper.StudentMapper;
import com.anishan.service.ScoreService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author anishan
 * @since 2024-03-23
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {

    @Resource
    StudentMapper studentMapper;
    @Resource
    ScoreMapper scoreMapper;
    @Override
    public RestEntity<List<StudentScore>> getScores(String name) {
        List<StudentScore> studentScores = studentMapper.selectStudentToStudentScore(name);
        for (StudentScore studentScore : studentScores) {
            List<Score> scores = scoreMapper.selectList(new QueryWrapper<Score>().eq("student_id", studentScore.getId()));
            studentScore.setScores(scores);
        }
        return RestEntity.success(studentScores, "Success");
    }

    @Override
    public String getScoreJson(String userId) {
        List<Score> scores = scoreMapper.selectScoresByUserId(userId);
        return RestEntity.success(scores, "success").toJson();
    }
}
