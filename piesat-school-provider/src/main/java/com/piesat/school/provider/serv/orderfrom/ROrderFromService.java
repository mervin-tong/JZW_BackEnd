package com.piesat.school.provider.serv.orderfrom;

import com.piesat.school.biz.ds.orderfrom.service.IOrderFromService;
import com.piesat.school.orderfrom.iservice.IROrderFromService;
import com.piesat.school.orderfrom.param.OrderFromParamData;
import com.piesat.school.orderfrom.vto.OrderFromVTO;
import com.piesat.school.user.iservice.IRUserService;
import com.smartwork.api.Result;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author suweipeng
 * @data 2022/2/24 10:47
 */
@DubboService(interfaceClass = IROrderFromService.class)
public class ROrderFromService implements IROrderFromService{

    @Autowired
    private IOrderFromService iOrderFromService;

    @Override
    public Result<List<OrderFromVTO>> orderFromMenu(Long dataType, Long downloadUserId) {
        return Result.ofSuccess(iOrderFromService.orderFromMenu(dataType,downloadUserId));
    }

    @Override
    public Result<OrderFromVTO> orderFromCreate(OrderFromParamData orderFromParamData) {
        return Result.ofSuccess(iOrderFromService.orderFromCreate(orderFromParamData));
    }
}
