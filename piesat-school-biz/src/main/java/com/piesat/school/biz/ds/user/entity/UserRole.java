package com.piesat.school.biz.ds.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_user_role")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 用户ID
	 */
//	@JSONField(serializeUsing= ToStringSerializer.class)
	private Long userId;

	/**
	 * 角色ID
	 */
	private Long roleId;

}
