package com.dpnice.iot.tutu.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author DPnice
 * @date 2019-12-15 上午 12:05
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class WaterQuantitySequential {

    @Id
    @NonNull
    @Column(nullable = false, length = 30)
    private String waterId;

    @NonNull
    @Column(nullable = false, length = 3)
    private double waterQuantity;

    @NonNull
    @Column(nullable = false, length = 20)
    private Date waterQuantityTime;
}
