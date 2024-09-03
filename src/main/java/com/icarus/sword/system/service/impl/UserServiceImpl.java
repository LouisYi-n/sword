package com.icarus.sword.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icarus.sword.system.entity.User;
import com.icarus.sword.system.mapper.UserMapper;
import com.icarus.sword.system.service.IUserService;
import com.icarus.sword.core.page.PageRequest;
import com.icarus.sword.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RedisService redisService;

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
        if (redisService.hasKey("user:" + id)) {
            log.info("get user from redis, user id: {}", id);
            return (User) redisService.get("user:" + id);
        }
        log.info("get user from db, user id: {}", id);
        User user = this.getById(id);
        redisService.set("user:" + id, user);
        return user;
    }
}
