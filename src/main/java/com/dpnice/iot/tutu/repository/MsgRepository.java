package com.dpnice.iot.tutu.repository;

import com.dpnice.iot.tutu.model.Msg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author DPnice
 * @date 2019-12-14 下午 11:12
 */
public interface MsgRepository extends JpaRepository<Msg, Integer> {

    List<Msg> findAllByCreateTimeAfter(Long time);

    List<Msg> findAllByCreateTimeGreaterThanEqual(Long time);

}
