package com.piesat.school.biz.ds.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piesat.school.biz.ds.user.entity.Email;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailMapper extends BaseMapper<Email> {
}
