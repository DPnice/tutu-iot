package com.dpnice.iot.tutu.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @author DPnice
 * @date 2019-12-17 下午 9:05
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class FeedRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedRecordId;

    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(nullable = false, length = 20)
    private Date feedRecordTime;
}
