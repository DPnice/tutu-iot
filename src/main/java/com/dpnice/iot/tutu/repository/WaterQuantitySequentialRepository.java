package com.dpnice.iot.tutu.repository;


import com.dpnice.iot.tutu.model.TemperatureSequential;
import com.dpnice.iot.tutu.model.WaterQuantitySequential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author DPnice
 * @date 2019-12-15 上午 12:07
 */
public interface WaterQuantitySequentialRepository extends JpaRepository<WaterQuantitySequential, String> {

    /**
     * 查询近一分钟的数据
     * @return list
     */
    @Query(value = "select * from water_quantity_sequential where water_quantity_time between date_add" +
            "(now(), interval - 1 minute) and now()", nativeQuery = true)
    List<WaterQuantitySequential> queryDataInOneMinute();

}
