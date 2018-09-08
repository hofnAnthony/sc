package com.dsx.transport.controller;

import com.alibaba.fastjson.JSONObject;
import com.dsx.transport.mapper.UserMapper;
import com.dsx.transport.model.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
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

    /**
     * 创建线程安全的Map
     */
    static Map<String, User> users = Collections.synchronizedMap(new HashMap<String, User>());

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public ResponseEntity<JSONObject> getUserById(@PathVariable(value = "id") Integer id) {
        JSONObject r = new JSONObject();
        try {
            User user = users.get(id);
            r.put("data", user);
            r.put("status", "ok");
        } catch (Exception e) {
            r.put("data", e.getClass().getName() + ":" + e.getMessage());
            r.put("status", "error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    /**
     * 查询用户列表
     *
     * @return
     */
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public ResponseEntity<JSONObject> getUserList() {
        JSONObject r = new JSONObject();
        try {
            List<User> userList = new ArrayList<User>(users.values());
            r.put("data", userList);
            r.put("status", "ok");
        } catch (Exception e) {
            r.put("data", e.getClass().getName() + ":" + e.getMessage());
            r.put("status", "error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "user", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> add(@RequestBody User user) {
        JSONObject r = new JSONObject();
        try {
            users.put(user.getId(), user);
            r.put("data", user.getId());
            r.put("status", "ok");
        } catch (Exception e) {
            r.put("data", e.getClass().getName() + ":" + e.getMessage());
            r.put("status", "error");

            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<JSONObject> delete(@PathVariable(value = "id") Integer id) {
        JSONObject r = new JSONObject();
        try {
            users.remove(id);
            r.put("data", id);
            r.put("status", "ok");
        } catch (Exception e) {
            r.put("data", e.getClass().getName() + ":" + e.getMessage());
            r.put("status", "error");

            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    /**
     * 根据id修改用户信息
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "更新信息", notes = "根据url的id来指定更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<JSONObject> update(@PathVariable("id") Integer id, @RequestBody User user) {
        JSONObject r = new JSONObject();
        try {
            User u = users.get(id);
            u.setUsername(user.getUsername());
            users.put("id", u);
            r.put("data", u);
            r.put("status", "ok");
        } catch (Exception e) {
            r.put("data", e.getClass().getName() + ":" + e.getMessage());
            r.put("status", "error");

            e.printStackTrace();
        }
        return ResponseEntity.ok(r);
    }

    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String jsonTest() {
        return " hi you!";
    }

}
