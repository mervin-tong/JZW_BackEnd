package com.piesat.school.biz.ds.datainf.builder;

import com.piesat.school.datainf.vto.MyDataInfVTO;
import com.smartwork.api.support.page.Page;
import com.smartwork.api.support.page.TailPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: liqiteng
 * @Date: 2023/01/12/14:41
 * @Description:
 */
public class PageBuilder {
    public static List<MyDataInfVTO> page(long current, long size, List<MyDataInfVTO> myDataInfVTOS) {
        List<MyDataInfVTO> myDataInfVTOList=new ArrayList<>();
        if (myDataInfVTOS!=null && myDataInfVTOS.size()>0){
            long currIdx = (current > 1 ? (current - 1) *  size : 0);
            for (int i=0;i< size && i<myDataInfVTOS.size()-currIdx;i++){
                MyDataInfVTO myDataInfVTO=myDataInfVTOS.get((int) currIdx + i);
                myDataInfVTOList.add(myDataInfVTO);
            }
        }
        return myDataInfVTOList;
    }
}
