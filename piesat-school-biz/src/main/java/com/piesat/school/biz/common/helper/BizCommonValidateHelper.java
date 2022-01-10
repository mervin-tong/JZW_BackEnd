package com.piesat.school.biz.common.helper;

import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.i18n.ResponseErrorCode;
import com.smartwork.api.errorcode.ReservedErrorCode;
import com.smartwork.api.exception.SmartworkI18nException;
import com.smartwork.api.param.UIDParamData;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * <p>
 *  工具类
 * </p>
 *
 * @author 唐子超
 * @since 2021-11-22
 */
public class BizCommonValidateHelper {
    /**
     * 验证实体参数或实体是否存在
     * @param id
     * @param service
     * @param <T>
     * @return
     */
    public static <T> T valdiateGetById(Serializable id, IService<T> service){
        if(id == null){
            throw new SmartworkI18nException(ReservedErrorCode.VALIDATION_DATA_NOT_EXIST);
        }
        T entity = service.getById(id);
        if(entity == null){
            throw new SmartworkI18nException(ReservedErrorCode.VALIDATION_DATA_NOT_EXIST);
        }
        return entity;
    }

    /**
     * 验证资源是否归属于当前访问用户
     * @param paramData 当前访问用户ID
     * @param targetUserId 资源归属用户ID
     */
    public static void validateUserOperationPermit(UIDParamData paramData, String targetUserId){
        if(StringUtils.isEmpty(targetUserId) || !targetUserId.equals(paramData.getUserId())){
            throw new SmartworkI18nException(ResponseErrorCode.USER_COMMON_OPERATION_PERMIT);
        }
    }


}
