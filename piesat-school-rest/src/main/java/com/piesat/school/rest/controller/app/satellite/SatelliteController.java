package com.piesat.school.rest.controller.app.satellite;

import com.piesat.school.satellite.iservice.IRSatelliteService;
import com.piesat.school.satellite.vto.SatelliteVTO;
import com.smartwork.api.Result;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/03/17/17:07
 * @Description:
 */


@Api(tags = "卫星图像")
@RestController
@RequestMapping("/app/satellite")
public class SatelliteController {
    @DubboReference
    private IRSatelliteService irsatellite;
    @GetMapping("/getAll")
    public Result<List<SatelliteVTO>> getAll(){
        return irsatellite.getAll();

    }
    @GetMapping("/test")
    public Result<List<SatelliteVTO>> test(){
        return irsatellite.test();
    }
}
