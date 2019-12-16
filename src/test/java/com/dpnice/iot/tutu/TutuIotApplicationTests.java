package com.dpnice.iot.tutu;

import com.dpnice.iot.tutu.model.TemperatureSequential;
import com.dpnice.iot.tutu.model.User;
import com.dpnice.iot.tutu.repository.TemperatureSequentialRepository;
import com.dpnice.iot.tutu.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class TutuIotApplicationTests {

    @Resource
    private TemperatureSequentialRepository temperatureSequentialRepository;

    @Resource
    private UserRepository userRepository;

    @Test
    void contextLoads() {

        List<TemperatureSequential> temperatureSequentials =
                temperatureSequentialRepository.queryDataInOneMinute();

        temperatureSequentials.forEach( v ->{
            System.out.println(v.getTemperature());
            System.out.println(v.getTemperatureTime());
        });
    }

    @Test
    void login() {
//        User dpnice = userRepository.findByUsername("dpnice");
//        System.out.println(dpnice.getUsername());

        List<User> all = userRepository.findAll();
        all.forEach(v->{
            System.out.println(v.getUsername());

        });
    }

}
