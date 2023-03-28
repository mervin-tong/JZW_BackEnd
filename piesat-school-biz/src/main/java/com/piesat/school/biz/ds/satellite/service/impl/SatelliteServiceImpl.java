package com.piesat.school.biz.ds.satellite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.biz.ds.job.utils.SatelliteUtils;
import com.piesat.school.biz.ds.satellite.entity.SatelliteEntity;
import com.piesat.school.biz.ds.satellite.mapper.SatelliteMapper;
import com.piesat.school.biz.ds.satellite.service.ISatelliteService;
import com.piesat.school.satellite.vto.SatelliteVTO;
import com.smartwork.api.Result;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/03/22/17:38
 * @Description:
 */
@Service
public class SatelliteServiceImpl extends ServiceImpl<SatelliteMapper, SatelliteEntity> implements ISatelliteService {
    @Override
    public Result<List<SatelliteVTO>> getAll() {
        List<SatelliteVTO> satelliteVTOS=new ArrayList<>();
        List<Date> fileName=new ArrayList<>();
        File file=new File("/LERSDA/JPSS");
        File[] listFiles= file.listFiles();
        if (listFiles != null) {
            for (File item:listFiles){
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
                try {
                    fileName.add(simpleDateFormat.parse(item.getName()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        Date dome= Collections.max(fileName);
        String simpleDateFormat=new SimpleDateFormat("yyyyMMdd").format(dome);
        File target=new File(file.getPath()+"/"+simpleDateFormat+"/images");
        File [] pic=target.listFiles();
        if (!Objects.isNull(pic)) {
            for (File it : pic) {
                SatelliteVTO satelliteVTO=new SatelliteVTO();
                satelliteVTO.setLocation(it.getPath().replace("\\","/"));
                File targ=new File(it.getParent());
                File sec=new File(targ.getParent());
                satelliteVTO.setDate(sec.getName());
                try {
                    BufferedImage image= ImageIO.read(new FileInputStream(it.getPath()));
                    satelliteVTO.setSize(image.getWidth()+"x"+image.getHeight());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                satelliteVTOS.add(satelliteVTO);
            }
        }
        return Result.ofSuccess(satelliteVTOS);
    }

}
