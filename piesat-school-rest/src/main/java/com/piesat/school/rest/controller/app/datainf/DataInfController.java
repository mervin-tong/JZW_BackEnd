package com.piesat.school.rest.controller.app.datainf;

import com.piesat.school.datainf.iservice.IRDataInfService;
import com.piesat.school.datainf.vto.DataInfVTO;
import com.smartwork.api.Result;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "数据信息模块")
@RestController
@RequestMapping("/app/datainf")
public class DataInfController {
    @DubboReference
    private IRDataInfService irDataInfService;
    @GetMapping("/datamenu")
    public Result<List<DataInfVTO>> getAllDataInf(){return irDataInfService.getAllDatainf();}
}
