package com.piesat.school.security.authorize;

import com.piesat.school.i18n.ResponseErrorCode;
import com.piesat.school.security.emuerlation.Logical;
import com.piesat.school.security.properties.AuthorizeProperties;
import com.smartwork.api.exception.SmartworkI18nException;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author 唐子超
 * @date 2021-12-13
 * <p>
 * 由于暂时未实现用户角色权限管理
 * 此处只做了基本的权限验证，通过Properties标识权限用户，
 * 后期替换为RBAC方式进行授权验证
 */
@RequiredArgsConstructor
//@Component
public class SimpleAuthorizeService implements IAuthorizeService{
    private final AuthorizeProperties authorizeProperties;

    @Override
    public void authorize(String userId, String[] checkPerms, Logical logical) {
        List<String> users = authorizeProperties.getUsers();
        if(CollectionUtils.isEmpty(users))
            throw new SmartworkI18nException(ResponseErrorCode.USER_AUTHORIZE_DENIED);

        if(userId == null || !users.contains(userId)){
            throw new SmartworkI18nException(ResponseErrorCode.USER_AUTHORIZE_DENIED);
        }
    }
}
