package com.piesat.school.rest.controller.app.datainf;

import com.piesat.school.datainf.iservice.IRDataInfService;
import com.piesat.school.datainf.param.DataInfSaveParamData;
import com.piesat.school.datainf.vto.DataInfVTO;
import com.smartwork.api.Result;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "数据信息模块")
@RestController
@RequestMapping("/app/datainf")
public class DataInfController {
    @DubboReference
    private IRDataInfService irDataInfService;
//    @Secured("ROLE_ADMIN")
    @GetMapping("/datamenu")
    public Result<List<DataInfVTO>> getAllDataInf(){return irDataInfService.getAllDatainf();}
    @PostMapping("/save")
    public Result<DataInfVTO> saveDataInf(@RequestBody DataInfSaveParamData paramData){
        return irDataInfService.saveDataInf(paramData);
    }
}
