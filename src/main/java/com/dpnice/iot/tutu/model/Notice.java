package com.dpnice.iot.tutu.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author DPnice
 * @date 2019-12-13 下午 8:05
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Notice {
    @Id
    @NonNull
    @GeneratedValue
    @Column(nullable = false, length = 30)
    private String noticeId;

    @NonNull
    @Column(nullable = false, length = 100)
    private String content;
}
