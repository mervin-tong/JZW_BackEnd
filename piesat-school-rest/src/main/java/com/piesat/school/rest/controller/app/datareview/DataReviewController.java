package com.piesat.school.rest.controller.app.datareview;

import com.piesat.school.datareview.iservice.IRDataReviewService;
import com.piesat.school.datareview.param.DataReviewParamData;
import com.piesat.school.datareview.vto.DataReviewVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    @PostMapping("/datareview")
    public Result<TailPage<DataReviewVTO>> dataReview(@RequestBody DataReviewParamData dataReviewParamData){
        return irDataReviewService.dataReview(dataReviewParamData);
    }

}
