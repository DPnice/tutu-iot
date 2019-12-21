package com.dpnice.iot.tutu.model;

import lombok.*;
import javax.persistence.*;

/**
 * @author DPnice
 * @date 2019-12-21 下午 1:58
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Msg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int msgId;

    @NonNull
    @Column(nullable = false)
    private String msg;

    @Column
    @NonNull
    private Long createTime;
}
