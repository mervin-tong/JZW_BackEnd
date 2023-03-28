package com.piesat.school.rest.controller.app.orderfrom;

import com.piesat.school.order.iservice.IROrderFromService;
import com.piesat.school.order.param.OrderFromAttentionParamData;
import com.piesat.school.order.param.OrderFromHistoryDownLoadParamData;
import com.piesat.school.order.param.OrderFromMenuPageParamData;
import com.piesat.school.order.param.OrderFromParamData;
import com.piesat.school.order.vto.OrderFromHistoryDownLoadVTO;
import com.piesat.school.order.param.*;
import com.piesat.school.order.vto.OrderFromAttentionVTO;
import com.piesat.school.order.vto.OrderFromInfoVTO;
import com.piesat.school.order.vto.OrderFromVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author suweipeng
 * @data 2022/2/24 10:18
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/app/order")
public class OrderFromController {
    @DubboReference
    private IROrderFromService irOrderFromService;
    @ApiOperation(value = "获取订单列表(分页)")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/menu")
    public Result<TailPage<OrderFromVTO>> orderFromMenu(OrderFromMenuPageParamData orderFromMenuPageParamData){
        return irOrderFromService.orderFromMenu(orderFromMenuPageParamData);
    }

    @ApiOperation(value = "新增订单")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/create")
    public Result<Boolean> orderFromCreate(@RequestBody OrderFromParamData orderFromParamData){
        return irOrderFromService.orderFromCreate(orderFromParamData);
    }
    @ApiOperation(value = "查看订单详情")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @GetMapping("/info")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderFromId", value = "订单id", dataType = "Long" ),
    })
    public Result<OrderFromInfoVTO> orderFromInfo(Long orderFromId){
        return irOrderFromService.orderFromInfo(orderFromId);
    }
    @ApiOperation(value = "关注列表（分页）")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/attentionList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pn", value = "第几页", dataType = "body" ),
            @ApiImplicitParam(name = "ps", value = "每页几个", dataType = "body" )
    })
    public Result<TailPage<OrderFromAttentionVTO>> attentionList(@RequestBody OrderFromAttentionParamData orderFromAttentionParamData){
        return irOrderFromService.attentionList(orderFromAttentionParamData);
    }
    @ApiOperation(value = "新增关注")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/saveAttention")
    public Result<Boolean> saveAttention(@RequestBody OrderFromAttentionSaveParamData orderFromAttentionSaveParamData){
        return irOrderFromService.saveAttention(orderFromAttentionSaveParamData);
    }

    @ApiOperation(value = "取消关注")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/delAttention")
    public Result<Boolean> delAttention(@RequestBody OrderFromAttentionDelParamData orderFromAttentionDelParamData){
        return irOrderFromService.delAttention(orderFromAttentionDelParamData);
    }

    @ApiOperation(value = "历史下载列表（分页）")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/historyDownload")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pn", value = "第几页", dataType = "body" ),
            @ApiImplicitParam(name = "ps", value = "每页几个", dataType = "body" )
    })
    public Result<TailPage<OrderFromHistoryDownLoadVTO>> historyDownload(OrderFromHistoryDownLoadParamData orderFromHistoryDownLoadParamData){
        return irOrderFromService.historyDownload(orderFromHistoryDownLoadParamData);
    }


    @ApiOperation(value = "查询关注列表数据是否以创建订单(data:trun/未下载)")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @GetMapping("/checkAttentionDatainf")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "checkUserId", value = "用户id", dataType = "Long" ),
            @ApiImplicitParam(name = "checkDataId", value = "数据id", dataType = "Long" )
    })
    public Result<Boolean> checkAttentionDatainf(Long checkUserId,Long checkDataId){
        return irOrderFromService.checkAttentionDatainf(checkUserId,checkDataId);
    }

    @ApiOperation(value = "订单列表删除(批量)")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @GetMapping("/orderfromDelete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderfromId", value = "要删除的订单号", dataType = "string" ),
    })
    public Result<Boolean> orderfromDelete(String orderfromId){
        return irOrderFromService.orderfromDelete(orderfromId);
    }

    @PostMapping("/auditOrder")
    public Result<OrderFromInfoVTO> auditOrder(OrderAuditParamData paramData){
        return irOrderFromService.auditOrder(paramData);
    }

    @ApiOperation(value = "订单签入签出")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @GetMapping("/checkInOrOut")
    public Result<List<OrderFromInfoVTO>> checkInOrOut(@RequestParam(value = "idList") List<Long> idList, Long userId, Integer checkStatus){
        return irOrderFromService.checkInOrOut(userId,idList,checkStatus);
    }

    @ApiOperation(value = "是否关注")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/isAttention")
    public Result<Integer> isAttention(@RequestBody OrderFromAttentionSaveParamData orderFromAttentionSaveParamData){
        return irOrderFromService.isAttention(orderFromAttentionSaveParamData);
    }

    @ApiOperation(value = "是否存在订单")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @GetMapping("/isOrder")
    public Result<Integer> isOrder(Long dataInfoId,Long downloadUserId){
        return irOrderFromService.isOrder(dataInfoId,downloadUserId);
    }

    @ApiOperation(value = "是否过期")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @GetMapping("/isOutOfDate")
    public Result<Integer> isOutOfDate(Long id){
        return irOrderFromService.isOutOfDate(id);
    }

}
