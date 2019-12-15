package com.dpnice.iot.tutu.repository;

import com.dpnice.iot.tutu.model.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author DPnice
 * @date 2019-12-14 下午 11:12
 */
public interface AlarmRepository extends JpaRepository<Alarm, String> {


}
