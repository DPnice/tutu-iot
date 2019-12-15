package com.dpnice.iot.tutu.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author DPnice
 * @date 2019-12-15 上午 2:32
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Alarm {

    @Id
    @NonNull
    @Column(nullable = false, length = 30)
    private String alarmId;

    @NonNull
    @Column(nullable = false, length = 30)
    private String alarm;

    @NonNull
    @Column(nullable = false, length = 8)
    private String type;

    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(nullable = false, length = 20)
    private Date alarmTime;

}
