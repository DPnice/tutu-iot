package com.dpnice.iot.tutu.service.impl;

import com.dpnice.iot.tutu.model.FeedRecord;
import com.dpnice.iot.tutu.model.HumiditySequential;
import com.dpnice.iot.tutu.model.TwoTuple;
import com.dpnice.iot.tutu.repository.FeedRecordRepository;
import com.dpnice.iot.tutu.service.FeedService;
import com.dpnice.iot.tutu.service.MqttServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author DPnice
 * @date 2019-12-17 下午 9:13
 */
@Service
public class FeedServiceImpl implements FeedService {

    private static Logger logger = LoggerFactory.getLogger(FeedServiceImpl.class);

    @Value("${mqtt.feedTopic}")
    private String feedTopic;

    private final MqttServer mqttServer;

    @Resource
    private final FeedRecordRepository feedRecordRepository;

    public FeedServiceImpl(MqttServer mqttServer, FeedRecordRepository feedRecordRepository) {
        this.mqttServer = mqttServer;
        this.feedRecordRepository = feedRecordRepository;
    }

    @Override
    public List<TwoTuple<String, Integer>> getFeedList() {
        List<FeedRecord> feedRecords = feedRecordRepository.queryDataInOneDay();
        Map<String, List<TwoTuple<String, Integer>>> collect = feedRecords.parallelStream().map(f -> {
            String key = returnTimeInterval(f.getFeedRecordTime());
            return new TwoTuple<>(key, 1);
        }).collect(Collectors.groupingBy(t -> t.key));
        List<TwoTuple<String, Integer>> re = new ArrayList<>();
        collect.forEach((x,y) -> re.add(new TwoTuple<>(x,y.size())));
        return re;
    }

    @Override
    public void feed() {
        try {
            mqttServer.publish(feedTopic, "feed", false, 0);
        } catch (Exception e) {
            logger.warn("喂食失败：{}", e.getMessage());
        }
    }


    private String returnTimeInterval(Date feedRecordTime) {
        String re;
        Calendar c = Calendar.getInstance();
        c.setTime(feedRecordTime);
        int day = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        if (hour > 0 && hour <= 4) {
            re = day + "day 00-04 hour";
        } else if (hour > 4 && hour <= 8) {
            re = day + "day 04-08 hour";
        } else if (hour > 8 && hour <= 12) {
            re = day + "day 08-12 hour";
        } else if (hour > 12 && hour <= 16) {
            re = day + "day 12-16 hour";
        } else if (hour > 16 && hour <= 20) {
            re = day + "day 16-20 hour";
        } else {
            re = day + "day 20-00 hour";
        }
        return re;
    }

}
