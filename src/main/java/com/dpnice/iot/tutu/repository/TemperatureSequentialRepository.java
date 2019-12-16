package com.dpnice.iot.tutu.repository;

import com.dpnice.iot.tutu.model.TemperatureSequential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author DPnice
 * @date 2019-12-14 下午 11:14
 */
public interface TemperatureSequentialRepository extends JpaRepository<TemperatureSequential, String> {

    /**
     * 查询近一分钟的数据
     * @return list
     */
    @Query(value = "select * from temperature_sequential where temperature_time between date_add" +
            "(now(), interval - 1 minute) and now()", nativeQuery = true)
    List<TemperatureSequential> queryDataInOneMinute();

}
