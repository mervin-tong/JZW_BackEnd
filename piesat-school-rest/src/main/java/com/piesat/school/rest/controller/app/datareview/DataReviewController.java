package com.piesat.school.rest.controller.app.datareview;

import com.piesat.school.datareview.iservice.IRDataReviewService;
import com.piesat.school.datareview.param.DataReviewParamData;
import com.piesat.school.datareview.param.UserDataReviewParamData;
import com.piesat.school.datareview.vto.DataReviewUserVTO;
import com.piesat.school.datareview.vto.DataReviewVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;


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
            @ApiImplicitParam(name = "ps", value = "每页几个", dataType = "body" )
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
            @ApiImplicitParam(name = "reviewUserId", value = "评审人id", dataType = "Long" )
    })
    public Result<Boolean> firstReview(Long dataReviewId,Long reviewUserId){
        return irDataReviewService.firstReview(dataReviewId,reviewUserId);
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
    public Result<TailPage<DataReviewUserVTO>> userDataReview( UserDataReviewParamData userDataReviewParamData){
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
            @ApiImplicitParam(name = "dataReviewId", value = "评审id", dataType = "Long" ),
            @ApiImplicitParam(name = "reviewUserId", value = "评审人id", dataType = "Long" ),
            @ApiImplicitParam(name = "status", value = "处理操作", dataType = "int" )

    })
    public Result<Boolean> userReview(Long dataReviewId,Long reviewUserId,int status){
        return irDataReviewService.userReview(dataReviewId,reviewUserId,status);
    }

    @ApiOperation(value = "复审")
    @ApiResponses({
            @ApiResponse(code=0,message="访问成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
            @ApiResponse(code=500,message="后台报错"),
    })
    @GetMapping("/recheck")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dataReviewId", value = "评审id", dataType = "Long" ),
            @ApiImplicitParam(name = "reviewUserId", value = "评审人id", dataType = "Long" ),
            @ApiImplicitParam(name = "status", value = "处理状态", dataType = "int" )

    })
    public Result<Boolean> recheck(Long dataReviewId,Long reviewUserId,int status){
        return irDataReviewService.recheck(dataReviewId,reviewUserId,status);
    }

















}
