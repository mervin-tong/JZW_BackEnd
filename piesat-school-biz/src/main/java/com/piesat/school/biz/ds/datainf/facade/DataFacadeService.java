package com.piesat.school.biz.ds.datainf.facade;

import com.piesat.school.biz.ds.datainf.service.IDatainfService;
import com.piesat.school.biz.ds.order.service.IOrderFromService;
import com.piesat.school.datainf.param.DataDetailParamData;
import com.piesat.school.datainf.vto.DataInfDetailVTO;
import com.piesat.school.order.vto.OrderFromInfoVTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class DataFacadeService {
    @Resource
    private IDatainfService dataInfService;
    @Resource
    private IOrderFromService orderFromService;
    public DataInfDetailVTO dataInfDetail(DataDetailParamData paramData) {
        DataInfDetailVTO dataInfDetailVTO=dataInfService.dataInfDetail(paramData.getId());
        if(paramData.getOrderId()!=null){
            OrderFromInfoVTO orderFromInfoVTO=orderFromService.orderFromInfo(paramData.getOrderId());
            dataInfDetailVTO.setDataType(orderFromInfoVTO.getDataType());
        }
        return dataInfDetailVTO;
    }
}
