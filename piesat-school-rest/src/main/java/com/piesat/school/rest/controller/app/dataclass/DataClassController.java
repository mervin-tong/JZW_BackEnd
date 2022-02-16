package com.piesat.school.rest.controller.app.dataclass;


import com.piesat.school.dataclass.iservice.IRDataClassService;
import com.piesat.school.dataclass.param.DataClassParamData;
import com.piesat.school.dataclass.vto.DataClassVTO;
import com.smartwork.api.Result;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 周悦尧
 * @date 2022-01-26
 * <p>
 * 数据分类相关
 */
@Api(tags = "数据分类模块")
@RestController
@RequestMapping("/app/dataclass")
public class DataClassController {
    @DubboReference
    private IRDataClassService dataclassService;
    @GetMapping("/menu")
    public Result<List<DataClassVTO>> getAllDataClass(){
        return dataclassService.getAllDataClass();
    };
    @PostMapping("/save")
    public Result<DataClassVTO> saveDataClass(@RequestBody DataClassParamData paramData){
        return dataclassService.saveDataClass(paramData);
    }
}
