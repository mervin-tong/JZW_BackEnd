package com.piesat.school.satellite.iservice;

import com.piesat.school.satellite.vto.SatelliteVTO;
import com.smartwork.api.Result;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/03/22/14:31
 * @Description:
 */
public interface IRSatelliteService {
    Result<List<SatelliteVTO>> getAll();
}
