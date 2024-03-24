package com.anishan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author anishan
 * @since 2024-03-23
 */
@Data
public class Score implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer score;

    private String projectName;

    private Integer studentId;

    @Override
    public String toString() {
        return "Score{" +
        "id = " + id +
        ", score = " + score +
        ", projectName = " + projectName +
        ", studentId = " + studentId +
        "}";
    }
}
