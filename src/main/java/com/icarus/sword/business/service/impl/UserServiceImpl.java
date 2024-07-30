package com.icarus.sword.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icarus.sword.business.entity.User;
import com.icarus.sword.business.mapper.UserMapper;
import com.icarus.sword.business.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
