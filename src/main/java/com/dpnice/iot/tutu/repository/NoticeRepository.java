package com.dpnice.iot.tutu.repository;

import com.dpnice.iot.tutu.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author DPnice
 * @date 2019-12-13 下午 8:27
 */
public interface NoticeRepository extends JpaRepository<Notice, String> {

}
