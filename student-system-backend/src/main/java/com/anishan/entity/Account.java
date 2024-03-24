package com.anishan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;

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
public class Account implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userId;

    private String password;

    private String role;

    @Override
    public String toString() {
        return "Account{" +
        "id = " + id +
        ", userId = " + userId +
        ", password = " + password +
        ", role = " + role +
        "}";
    }
}
