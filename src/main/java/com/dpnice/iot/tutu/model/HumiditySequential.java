package com.dpnice.iot.tutu.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author DPnice
 * @date 2019-12-13 下午 4:50
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class HumiditySequential {

    @Id
    @NonNull
    @Column(nullable = false, length = 30)
    private String humidityId;

    @NonNull
    @Column(nullable = false, length = 3)
    private double humidity;

    @NonNull
    @Column(nullable = false, length = 20)
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date humidityTime;

}
