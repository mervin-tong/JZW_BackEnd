package com.piesat.school.security.authorize;

import com.piesat.school.security.emuerlation.Logical;

/**
 * @author 唐子超
 * @date 2021-12-13
 * <p>
 * 定义权限验证
 */
public interface IAuthorizeService {
    void authorize(String userId, String[] checkPerms, Logical logical);
}
