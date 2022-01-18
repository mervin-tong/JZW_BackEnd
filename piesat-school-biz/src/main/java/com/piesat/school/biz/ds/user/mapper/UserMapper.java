package com.piesat.school.biz.ds.user.mapper;

import com.piesat.school.biz.ds.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 唐子超
 * @since 2022-01-16
 */
public interface UserMapper extends BaseMapper<User> {

    User getUser();
}
