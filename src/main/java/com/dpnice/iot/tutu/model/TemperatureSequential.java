package com.dpnice.iot.tutu.model;

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
public class TemperatureSequential {

    @Id
    @NonNull
    @GeneratedValue
    @Column(nullable = false, length = 30)
    private String temperatureId;

    @NonNull
    @Column(nullable = false, length = 3)
    private double temperature;

    @NonNull
    @Column(nullable = false, length = 20)
    private Date temperatureTime;

}
