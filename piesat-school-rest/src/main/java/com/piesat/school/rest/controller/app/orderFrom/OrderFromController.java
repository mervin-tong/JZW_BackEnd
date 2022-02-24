package com.piesat.school.rest.controller.app.orderFrom;

import com.piesat.school.datainf.vto.DataInfVTO;
import com.piesat.school.orderfrom.iservice.IROrderFromService;
import com.piesat.school.orderfrom.param.OrderFromParamData;
import com.piesat.school.orderfrom.vto.OrderFromVTO;
import com.smartwork.api.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author suweipeng
 * @data 2022/2/24 10:18
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/app/orderfrom")
public class OrderFromController {
    @DubboReference
    private IROrderFromService irOrderFromService;
    @ApiOperation(value = "获取订单列表")
    @GetMapping("/menu")
    public Result<List<OrderFromVTO>> orderFromMenu(Long dataType, Long downloadUserId){
        return irOrderFromService.orderFromMenu(dataType,downloadUserId);
    }
    @ApiOperation(value = "新增订单")
    @PostMapping("/create")
    public Result<OrderFromVTO> orderFromCreate(@RequestBody OrderFromParamData orderFromParamData){
        return irOrderFromService.orderFromCreate(orderFromParamData);
    }
}
