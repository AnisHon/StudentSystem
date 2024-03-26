package com.anishan.mapper;

import com.anishan.entity.Student;
import com.anishan.entity.StudentScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
public interface StudentMapper extends BaseMapper<Student> {
    @Select("select s.id as id, s.name as name, account.user_id as accountId from student s left join account on s.account_id = account.id where name like concat('%',#{name,jdbcType=VARCHAR},'%')")
    List<StudentScore> selectStudentToStudentScore(String name);

    @Select("select s.id as id, s.name as name, a.user_id as accountId from student s left join db02.account a on s.account_id = a.id")
    List<Student> selectStudent();

    @Select("select s.id from student s left join account a on a.id = s.account_id where a.user_id = #{userId}")
    Integer getStudentIdByUserId(String userId);
}
