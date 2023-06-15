package com.piesat.school.biz.ds.satellite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.biz.ds.satellite.entity.SatelliteEntity;
import com.piesat.school.satellite.vto.SatelliteVTO;
import com.smartwork.api.Result;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/03/22/17:27
 * @Description:
 */
public interface ISatelliteService extends IService<SatelliteEntity> {
    Result<List<SatelliteVTO>> getAll();

    Result<List<SatelliteVTO>> test();
}
