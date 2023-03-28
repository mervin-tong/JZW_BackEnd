package com.piesat.school.provider.serv.satellite;

import com.piesat.school.biz.ds.satellite.service.ISatelliteService;
import com.piesat.school.satellite.iservice.IRSatelliteService;
import com.piesat.school.satellite.vto.SatelliteVTO;
import com.smartwork.api.Result;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/03/22/17:24
 * @Description:
 */
@DubboService(interfaceClass = IRSatelliteService.class)
public class RSatelliteService implements IRSatelliteService {
    @Resource
    private ISatelliteService iSatelliteService;

    @Override
    public Result<List<SatelliteVTO>> getAll() {
        return iSatelliteService.getAll();
    }
}
