package com.piesat.school.rest.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piesat.school.aspectj.iservice.IRAspectService;
import com.piesat.school.security.JWT.TokenUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.dubbo.config.annotation.DubboReference;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Date;

@Aspect
@Component
public class RequestAspect {

    @DubboReference
    private IRAspectService aspectService;

    @Around(value = "@annotation(aroundRecord)")
    public Object aroundLog(ProceedingJoinPoint point, AroundRecord aroundRecord) {
        StringBuilder sb = new StringBuilder();
        StopWatch started = new StopWatch();
        StringBuilder params= new StringBuilder();
        Date operationTime = new Date();
        started.start();
        try {
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();
//            String methodName = method.getName();
//            String name = signature.getName();

            ObjectMapper mapper = new ObjectMapper();
            Parameter[] parameters = method.getParameters();
            Object[] args = point.getArgs();
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    String paramName = parameters[i].getName();
                    String argStr= null;
                    try {
                        argStr  = mapper.writeValueAsString(args[i]);
                    } catch (Exception e) {
                        argStr = "无法解析";
                    }
                    params.append(paramName).append(":").append(argStr).append(" ");
                }
            }
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request =  attributes.getRequest();
            String url =request.getRequestURI();
            String tokenHeader = request.getHeader("token");
            String token="";
            String username="";
            if(tokenHeader !=null) {
                token= tokenHeader.replace(TokenUtils.TOKEN_PREFIX, "");
                username = TokenUtils.getUsername(token);
            }
            Boolean a =aspectService.addRecord(url,params,username,operationTime);
            return point.proceed();
//            return true;
        } catch (RuntimeException e) {
            params.append("RuntimeException:>").append(e.getMessage()).append("\n");
            throw e;
        } catch (Throwable throwable) {
            params.append("Throwable:>").append(throwable.getMessage()).append("\n");
            throw new RuntimeException("系统异常!");
        }finally {
            started.stop();

        }
    }
}
