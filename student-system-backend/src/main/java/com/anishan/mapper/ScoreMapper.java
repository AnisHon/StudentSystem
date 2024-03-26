package com.anishan.mapper;

import com.anishan.entity.Score;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author anishan
 * @since 2024-03-23
 */
@Mapper
public interface ScoreMapper extends BaseMapper<Score> {


    @Select("select s2.id, s2.score, s2.project_name from account a left join db02.student s on a.id = s.account_id left outer join db02.score s2 on s.id = s2.student_id where a.user_id = #{user_id}")
    List<Score> selectScoresByUserId(String userId);
}
