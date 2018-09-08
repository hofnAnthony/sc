package com.dsx.transport.controller;

import com.dsx.transport.mapper.UserMapper;
import com.dsx.transport.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author ydy
 */
@RestController
@RequestMapping(value = "/api/")
public class AppController {

    private static final Logger log = LoggerFactory.getLogger(AppController.class);

    private static int expires = 3 * 60;

    private UserMapper userMapper;
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public AppController(UserMapper userMapper, RedisTemplate<String, String> redisTemplate) {
        this.userMapper = userMapper;
        this.redisTemplate = redisTemplate;
    }

    /**
     * register user
     *
     * @param name
     * @param password
     * @return
     */
    @RequestMapping(value = "create/{name}/{password}")
    public String create(@PathVariable("name") String name, @PathVariable("password") String password) {
        log.info("接收用户信息,name = {},pass = {}", new String[]{name, password});
        User user = new User();
        String uuid = String.valueOf(UUID.randomUUID());
        user.setId(uuid);
        user.setUsername(name);
        user.setPassword(password);
        userMapper.insert(user);

        redisTemplate.opsForValue().set(user.getId(), user.getUsername(), expires, TimeUnit.SECONDS);

        return "success";
    }
}
