package com.dpnice.iot.tutu.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author DPnice
 * @date 2019-10-23 下午 5:19
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 1, max = 50)
    private String username;

    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    @Size(max = 100)
    private String authorityString;

    //过期时间加长
    @Transient
    private Boolean rememberMe;

    public Boolean isRememberMe() {
        return rememberMe;
    }

}
