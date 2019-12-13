package com.dpnice.iot.tutu.service.impl;

import com.dpnice.iot.tutu.model.Notice;
import com.dpnice.iot.tutu.repository.NoticeRepository;
import com.dpnice.iot.tutu.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author DPnice
 * @date 2019-12-13 下午 8:30
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private final NoticeRepository noticeRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Override
    public Notice getNotice() {
        Optional<Notice> byId = noticeRepository.findById("1");
        return byId.orElse(null);
    }
}
