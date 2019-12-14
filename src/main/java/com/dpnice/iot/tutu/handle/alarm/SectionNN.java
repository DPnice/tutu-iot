package com.dpnice.iot.tutu.handle.alarm;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @author DPnice
 * @date 2019-12-15 上午 3:02
 * ()
 */
@Service
public class SectionNN implements SectionComparison, InitializingBean {

    private SectionType type = SectionType.NN;

    @Override
    public void afterPropertiesSet() throws Exception {
        SectionFactory.register(type, this);
    }
}
