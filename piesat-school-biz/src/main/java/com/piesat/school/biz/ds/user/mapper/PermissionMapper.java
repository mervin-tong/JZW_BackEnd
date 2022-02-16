package com.piesat.school.biz.ds.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piesat.school.biz.ds.user.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    Set<Permission> getPermissionByRoleId(Integer id);
    //查询该Url所需的权限
    Set<Permission> getPermissionByUrl(String path);




}
