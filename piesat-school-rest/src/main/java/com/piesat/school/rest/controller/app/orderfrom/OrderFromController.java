package com.piesat.school.rest.controller.app.orderfrom;

import com.piesat.school.orderfrom.iservice.IROrderFromService;
import com.piesat.school.orderfrom.param.OrderFromAttentionParamData;
import com.piesat.school.orderfrom.param.OrderFromHistoryDownLoadParamData;
import com.piesat.school.orderfrom.param.OrderFromMenuPageParamData;
import com.piesat.school.orderfrom.param.OrderFromParamData;
import com.piesat.school.orderfrom.vto.OrderFromHistoryDownLoadVTO;
import com.piesat.school.orderfrom.param.*;
import com.piesat.school.orderfrom.vto.OrderFromAttentionVTO;
import com.piesat.school.orderfrom.vto.OrderFromInfoVTO;
import com.piesat.school.orderfrom.vto.OrderFromVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation(value = "获取订单列表(分页)")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/menu")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pn", value = "第几页", dataType = "body" ),
            @ApiImplicitParam(name = "ps", value = "每页几个", dataType = "body" )
    })
    public Result<TailPage<OrderFromVTO>> orderFromMenu(@RequestBody OrderFromMenuPageParamData orderFromMenuPageParamData){
        return irOrderFromService.orderFromMenu(orderFromMenuPageParamData);
    }

    @ApiOperation(value = "新增订单")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/create")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pn", value = "第几页", dataType = "body" ),
            @ApiImplicitParam(name = "ps", value = "每页几个", dataType = "body" )
    })
    public Result<OrderFromVTO> orderFromCreate(@RequestBody OrderFromParamData orderFromParamData){
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
    @PostMapping("/historydownload")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pn", value = "第几页", dataType = "body" ),
            @ApiImplicitParam(name = "ps", value = "每页几个", dataType = "body" )
    })
    public Result<TailPage<OrderFromHistoryDownLoadVTO>> historydownload(@RequestBody OrderFromHistoryDownLoadParamData orderFromHistoryDownLoadParamData){
        return irOrderFromService.historyDownload(orderFromHistoryDownLoadParamData);
    }
}
