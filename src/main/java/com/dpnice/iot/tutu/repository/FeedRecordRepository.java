package com.dpnice.iot.tutu.repository;

import com.dpnice.iot.tutu.model.FeedRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author DPnice
 * @date 2019-12-17 下午 9:07
 */
public interface FeedRecordRepository  extends JpaRepository<FeedRecord, String> {
    /**
     * 查询近一天分钟的数据
     * @return list
     */
    @Query(value = "select * from feed_record where feed_record_time between date_add" +
            "(now(), interval - 7 day) and now()", nativeQuery = true)
    List<FeedRecord> queryDataInOneDay();

}
