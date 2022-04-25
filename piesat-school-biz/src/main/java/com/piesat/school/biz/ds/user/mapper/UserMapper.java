package com.piesat.school.biz.ds.user.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piesat.school.user.vto.UserVTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 唐子超
 * @since 2022-01-16
 */

public interface UserMapper extends BaseMapper<User> {

    List<UserVTO> getAdminList(Page<UserVTO> page);
}
