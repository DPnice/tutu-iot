package com.dpnice.iot.tutu.repository;

import com.dpnice.iot.tutu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author DPnice
 * @date 2019-10-24 下午 2:22
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 根据用户名查找
     *
     * @param username username
     * @return User
     */
    User findByUsername(String username);

}
