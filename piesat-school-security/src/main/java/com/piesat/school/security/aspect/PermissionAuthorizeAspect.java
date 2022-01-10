package com.piesat.school.security.aspect;

import com.piesat.school.security.annotation.RequiresPermissions;
import com.piesat.school.security.authorize.SimpleAuthorizeService;
import com.smartwork.api.context.PieGatewayContextHolder;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 切面拦截功能权限验证
 * @author 唐子超
 */
//@Order()
@Aspect
//@Component
@RequiredArgsConstructor
public class PermissionAuthorizeAspect {
    //public final AuthorizeContextRedisService authorizeContextRedisService;
    public final SimpleAuthorizeService authorizeService;

    /**
     * 定义AOP签名 (切入所有使用鉴权注解的方法)
     */
    public static final String POINTCUT_SIGN = " @annotation(com.piesat.school.security.annotation.RequiresPermissions)";

    @Pointcut(POINTCUT_SIGN)
    public void pointcut() {
    }

    /*@Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 注解鉴权
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        checkMethodAnnotation(signature.getMethod());
        try {
            // 执行原有逻辑
            Object obj = joinPoint.proceed();
            return obj;
        } catch (Throwable e) {
            throw e;
        }
    }*/

    @Before("pointcut()")
    public void permissionAuthorize(JoinPoint point) throws Throwable {
        //UserContext userContext = authorizeContextRedisService.validateAndGetUserContext(PieGatewayContextHolder.getUid());
        // 注解鉴权
        MethodSignature signature = (MethodSignature) point.getSignature();
        checkMethodAnnotation(signature.getMethod());
    }

    /**
     * 由于暂时未实现用户角色权限管理
     * 此处只做了基本的权限验证，通过Properties标识权限用户
     */
    public void checkMethodAnnotation(Method method){
        // 校验 @RequiresPermissions 注解
        RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
        if (requiresPermissions != null) {
            authorizeService.authorize(PieGatewayContextHolder.getUid(), requiresPermissions.value(), requiresPermissions.logical());
        }
    }

    /**
     * 对一个Method对象进行注解检查
     */
    /*public void checkMethodAnnotation(Method method, UserContext userContext) {
        // 校验 @RequiresPermissions 注解
        RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
        if (requiresPermissions != null) {
            Set<String> perms = userContext.getAuthority().getPerms();
            if(CollectionUtils.isEmpty(perms))
                throw new SmartworkI18nException(ResponseErrorCode.USER_PERMISSION_DENIED);

            String[] needPerms = requiresPermissions.value();
            if (requiresPermissions.logical() == Logical.AND) {
                for (String needPerm : needPerms) {
                    boolean match = perms.stream().filter(StringUtils::hasText).anyMatch(x -> PatternMatchUtils.simpleMatch(x, needPerm));
                    if (!match) {
                        throw new SmartworkI18nException(ResponseErrorCode.USER_PERMISSION_DENIED);
                    }
                }
            } else {
                for (String needPerm : needPerms) {
                    boolean match = perms.stream().filter(StringUtils::hasText).anyMatch(x -> PatternMatchUtils.simpleMatch(x, needPerm));
                    if (match) {
                        return;
                    }
                }
                throw new SmartworkI18nException(ResponseErrorCode.USER_PERMISSION_DENIED);
            }
        }
    }*/
}
