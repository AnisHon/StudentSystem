package com.anishan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

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
public class Teacher implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer accountId;

    @Override
    public String toString() {
        return "Teacher{" +
        "id = " + id +
        ", name = " + name +
        ", accountId = " + accountId +
        "}";
    }
}
