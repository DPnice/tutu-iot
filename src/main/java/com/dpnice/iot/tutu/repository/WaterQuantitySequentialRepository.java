package com.dpnice.iot.tutu.repository;


import com.dpnice.iot.tutu.model.WaterQuantitySequential;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author DPnice
 * @date 2019-12-15 上午 12:07
 */
public interface WaterQuantitySequentialRepository extends JpaRepository<WaterQuantitySequential, String> {

}
