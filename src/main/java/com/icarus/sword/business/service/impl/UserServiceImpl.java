package com.icarus.sword.business.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icarus.sword.business.entity.User;
import com.icarus.sword.business.mapper.UserMapper;
import com.icarus.sword.business.service.IUserService;
import com.icarus.sword.core.page.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public Page<User> getUserByPage(PageRequest pageRequest) {
        return this.page(new Page<>(pageRequest.getPageNo(), pageRequest.getPageSize()));
    }

    @Override
    public Page<User> getUserByPage2(int pageNo, int pageSize) {
        return this.page(new Page<>(pageNo, pageSize));
    }
}
