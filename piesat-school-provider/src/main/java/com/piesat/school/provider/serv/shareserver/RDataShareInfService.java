package com.piesat.school.provider.serv.shareserver;

import com.piesat.school.datainf.iservice.IRDataShareInfService;
import com.piesat.school.order.iservice.IROrderFromService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(interfaceClass = IRDataShareInfService.class)
public class RDataShareInfService implements IRDataShareInfService {
}
