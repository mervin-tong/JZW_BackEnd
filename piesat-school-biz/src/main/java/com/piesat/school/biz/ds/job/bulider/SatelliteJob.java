package com.piesat.school.biz.ds.job.bulider;

import com.piesat.school.biz.ds.job.utils.SatelliteUtils;
import com.piesat.school.biz.ds.satellite.entity.SatelliteEntity;
import com.piesat.school.biz.ds.satellite.mapper.SatelliteMapper;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/03/20/10:00
 * @Description: 定时任务，获取文件夹中最新的卫星图像并更新
 */
public class SatelliteJob extends QuartzJobBean {
    @Resource
    private SatelliteMapper satelliteMapper;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SatelliteEntity satelliteEntity=new SatelliteEntity();
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

        Date newName= Collections.max(fileName);
        String simpleDateFormat=new SimpleDateFormat("yyyyMMdd").format(newName);
        File target=new File(file.getPath()+"/"+simpleDateFormat);
        File [] pic=target.listFiles();
        SatelliteUtils satelliteUtils=new SatelliteUtils();
        if (pic.length>0) {
            for (File it : pic) {
                satelliteEntity.setLocation(it.getPath());
                satelliteEntity.setDate(it.getParent());
                System.out.println("图片地址:"+it.getPath());

                System.out.println("图片大小:"+ satelliteUtils.toSize(it.length()));
                try {
                    BufferedImage image= ImageIO.read(new FileInputStream(it.getPath()));
                    System.out.println("图片尺寸:"+image.getWidth()+"x"+image.getHeight());
                    satelliteEntity.setSize(image.getWidth()+"x"+image.getHeight());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }




    }
}
