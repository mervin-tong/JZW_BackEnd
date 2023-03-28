package com.piesat.school.biz.ds.job.utils;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/03/22/16:38
 * @Description:
 */
public class SatelliteUtils {

    public String toSize(Long size){
        if (size<1024){ return String.valueOf(size)+"B"; }
        else { size = size / 1024; }
        if (size<1024){return String.valueOf(size)+"KB";}
        else { size = size/1024;}
        if (size<1024){
            size=size*100;
            return String.valueOf((size/100)+"."+(size%100))+"MB";}
        else {
            size = size*100/1024;
            return String.valueOf((size/100)+"."+(size%100))+"GB";
        }
    }
}
