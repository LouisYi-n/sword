package com.sword.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sword.core.RedisCache;
import com.sword.page.PageRequest;
import com.sword.system.entity.User;
import com.sword.system.mapper.UserMapper;
import com.sword.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RedisCache redisCache;

    @Override
    public Page<User> getUserByPage(PageRequest pageRequest) {
        return this.page(new Page<>(pageRequest.getPageNo(), pageRequest.getPageSize()));
    }

    @Override
    public Page<User> getUserByPage2(int pageNo, int pageSize) {
        return this.page(new Page<>(pageNo, pageSize));
    }

    @Override
    public User getUserById(Long id) {
        if (redisCache.hasKey("user:" + id)) {
            log.info("get user from redis, user id: {}", id);
            return (User) redisCache.get("user:" + id);
        }
        log.info("get user from db, user id: {}", id);
        User user = this.getById(id);
        redisCache.set("user:" + id, user);
        return user;
    }
}
