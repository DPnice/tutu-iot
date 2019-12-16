package com.dpnice.iot.tutu.repository;

import com.dpnice.iot.tutu.model.HumiditySequential;
import com.dpnice.iot.tutu.model.TemperatureSequential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author DPnice
 * @date 2019-12-14 下午 11:12
 */
public interface HumiditySequentialRepository extends JpaRepository<HumiditySequential, String> {

    /**
     * 查询近一分钟的数据
     * @return list
     */
    @Query(value = "select * from humidity_sequential where humidity_time between date_add" +
            "(now(), interval - 1 minute) and now()", nativeQuery = true)
    List<HumiditySequential> queryDataInOneMinute();
}
