package com.icarus.sword.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.icarus.sword.business.entity.User;
import com.icarus.sword.core.page.PageRequest;


public interface IUserService extends IService<User> {
    Page<User> getUserByPage(PageRequest pageRequest);

    Page<User> getUserByPage2(int pageNo, int pageSize);
}
