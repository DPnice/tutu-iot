package com.dpnice.iot.tutu.repository;

import com.dpnice.iot.tutu.model.TemperatureSequential;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author DPnice
 * @date 2019-12-14 下午 11:14
 */
public interface TemperatureSequentialRepository  extends JpaRepository<TemperatureSequential, String> {

}
