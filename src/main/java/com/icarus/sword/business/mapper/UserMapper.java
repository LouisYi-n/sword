package com.icarus.sword.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icarus.sword.business.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
