package com.dsx.transport.mapper;

import com.dsx.transport.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ydy
 */
@Repository
public interface UserMapper {

    /**
     * get all
     *
     * @return
     */
    List<User> getAll();

    /**
     * getOne
     *
     * @return
     */
    User getOne(String id);

    /**
     * insert
     *
     * @return
     */
    void insert(User user);

    /**
     * update
     *
     * @return
     */
    void update(User user);

    /**
     * delete
     *
     * @return
     */
    void delete(Long id);

    /**
     * get password
     *
     * @param username 用户名
     */
    String getPassword(String username);

    /**
     * 获得角色权限
     *
     * @param username 用户名
     * @return user/admin
     */
    String getRole(String username);
}
