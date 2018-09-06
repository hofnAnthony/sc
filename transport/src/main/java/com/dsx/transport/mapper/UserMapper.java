package com.dsx.transport.mapper;

import com.dsx.transport.model.User;

import java.util.List;

/**
 * @author ydy
 */

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
}
