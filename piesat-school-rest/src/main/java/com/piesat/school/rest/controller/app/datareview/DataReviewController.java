package com.piesat.school.rest.controller.app.datareview;

import com.piesat.school.base.PageQueryParamData;
import com.piesat.school.datareview.iservice.IRDataReviewService;
import com.piesat.school.datareview.param.ConditionScreenParamData;
import com.piesat.school.datareview.param.DataReviewParamData;
import com.piesat.school.datareview.param.UserDataReviewParamData;
import com.piesat.school.datareview.vto.DataReviewListVTO;
import com.piesat.school.datareview.vto.DataReviewReVTO;
import com.piesat.school.datareview.vto.DataReviewVTO;
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
@Api(tags = "数据评审")
@RestController
@RequestMapping("/app/orderfrom")
public class DataReviewController {
    @DubboReference
    private IRDataReviewService irDataReviewService;

    @ApiOperation(value = "数据评审列表（分页）")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/datareview")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pn", value = "第几页", dataType = "body" ),
            @ApiImplicitParam(name = "ps", value = "每页几个", dataType = "body" ),
            @ApiImplicitParam(name = "dataName", value = "数据名称", dataType = "String" ),
            @ApiImplicitParam(name = "start", value = "起始时间", dataType = "String" ),
            @ApiImplicitParam(name = "end", value = "终止时间", dataType = "String" ),
            @ApiImplicitParam(name = "status", value = "评审状态", dataType = "String" )
    })
    public Result<TailPage<DataReviewVTO>> dataReview(@RequestBody DataReviewParamData dataReviewParamData){
        return irDataReviewService.dataReview(dataReviewParamData);
    }


    @ApiOperation(value = "初次审批")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @GetMapping("/firstreview")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dataReviewId", value = "评审id", dataType = "Long" ),
            @ApiImplicitParam(name = "reviewUserId", value = "评审人id", dataType = "Long" ),
            @ApiImplicitParam(name = "isPass", value = "是否通过 0不通过 1通过", dataType = "Integer" ),
            @ApiImplicitParam(name = "reason", value = "原因", dataType = "String" )
    })
    public Result<Boolean> firstReview(Long dataReviewId,Long reviewUserId,Integer isPass,String reason){
        return irDataReviewService.firstReview(dataReviewId,reviewUserId,isPass,reason);
    }

    @ApiOperation(value = "指定专家评审")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @GetMapping("/assign")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dataReviewId", value = "评审id", dataType = "Long" ),
            @ApiImplicitParam(name = "expertId", value = "指定专家的id", dataType = "Long" ),
            @ApiImplicitParam(name = "reviewUserId", value = "评审人id", dataType = "Long" )

    })
    public Result<Boolean> assign(Long dataReviewId,Long expertId,Long reviewUserId){
        return irDataReviewService.assign(dataReviewId,expertId,reviewUserId);
    }

    @ApiOperation(value = "用户评审列表（分页）")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/userdatareview")
    public Result<DataReviewListVTO> userDataReview(UserDataReviewParamData userDataReviewParamData){
        return irDataReviewService.userDataReview(userDataReviewParamData);
    }

    @ApiOperation(value = "专家评审处理")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @PostMapping("/userreview")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dataId", value = "评审id", dataType = "Long" ),
            @ApiImplicitParam(name = "reviewUserId", value = "评审人id", dataType = "Long" ),
            @ApiImplicitParam(name = "reason", value = "原因", dataType = "Long" ),
            @ApiImplicitParam(name = "status", value = "处理操作 0不通过 1通过", dataType = "int" )
    })
    public Result<Boolean> userReview(Long dataId,Long reviewUserId,int status,String reason){
        return irDataReviewService.userReview(dataId,reviewUserId,status,reason);
    }

    @ApiOperation(value = "发布上架")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @GetMapping("/release")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reviewUserId", value = "评审人id", dataType = "Long" ),
            @ApiImplicitParam(name = "dataId", value = "数据id", dataType = "Long" )
    })
    public Result<Boolean> release(Long reviewUserId,int dataId){
        return irDataReviewService.release(reviewUserId,dataId);
    }

    @ApiOperation(value = "查询条件筛选")
    @GetMapping("/screen")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "target", value = "目标参数", dataType = "String" ),
            @ApiImplicitParam(name = "dataName", value = "标题名称", dataType = "String" ),
            @ApiImplicitParam(name = "status", value = "评审状态", dataType = "String" )
    })
    public Result<List<String>> screen(ConditionScreenParamData paramData){
        return irDataReviewService.screen(paramData);
    }

    @ApiOperation(value = "签入签出")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @GetMapping("/checkInOrOut")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "评审人id ", dataType = "Lond" ),
            @ApiImplicitParam(name = "dataList", value = "数据id列表", dataType = "List<Long>" ),
            @ApiImplicitParam(name = "checkStatus", value = "1签入 0签出", dataType = "int" )
    })
    public Result<List<DataReviewReVTO>> checkInOrOut(@RequestParam(value = "dataList") List<Long> dataList,Long userId, Integer checkStatus){
        return irDataReviewService.checkInOrOut(userId,dataList,checkStatus);
    }

    @ApiOperation(value = "查询评审信息")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @GetMapping("/selectReviewInfo")
    public Result<TailPage<DataReviewReVTO>> selectReviewInfo(@RequestParam(value = "data") Long dataId,PageQueryParamData paramData){
        return irDataReviewService.selectReviewInfo(dataId,paramData);
    }

}
