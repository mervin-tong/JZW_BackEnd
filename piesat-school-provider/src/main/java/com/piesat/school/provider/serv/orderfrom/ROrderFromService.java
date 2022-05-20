package com.piesat.school.provider.serv.orderfrom;

import com.piesat.school.biz.ds.order.service.IAttentionService;
import com.piesat.school.biz.ds.order.service.IOrderFromService;
import com.piesat.school.order.iservice.IROrderFromService;
import com.piesat.school.order.param.OrderFromAttentionParamData;
import com.piesat.school.order.param.OrderFromHistoryDownLoadParamData;
import com.piesat.school.order.param.OrderFromMenuPageParamData;
import com.piesat.school.order.param.OrderFromParamData;
import com.piesat.school.order.param.*;
import com.piesat.school.order.vto.OrderFromAttentionVTO;
import com.piesat.school.order.vto.OrderFromHistoryDownLoadVTO;
import com.piesat.school.order.vto.OrderFromInfoVTO;
import com.piesat.school.order.vto.OrderFromVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.TailPage;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author suweipeng
 * @data 2022/2/24 10:47
 */
@DubboService(interfaceClass = IROrderFromService.class)
public class ROrderFromService implements IROrderFromService {

    @Autowired
    private IOrderFromService iOrderFromService;

    @Autowired
    private IAttentionService iAttentionService;

    //获取订单列表
    @Override
    public Result<TailPage<OrderFromVTO>> orderFromMenu(OrderFromMenuPageParamData orderFromMenuPageParamData) {
        return Result.ofSuccess(iOrderFromService.orderFromMenu(orderFromMenuPageParamData));
    }

    //创建订单
    @Override
    public Result<Boolean> orderFromCreate(OrderFromParamData orderFromParamData) {
        return Result.ofSuccess(iOrderFromService.orderFromCreate(orderFromParamData));
    }

    //订单详情
    @Override
    public Result<OrderFromInfoVTO> orderFromInfo(Long orderFromId) {
        return Result.ofSuccess(iOrderFromService.orderFromInfo(orderFromId));
    }

    //关注列表
    @Override
    public Result<TailPage<OrderFromAttentionVTO>> attentionList(OrderFromAttentionParamData orderFromAttentionParamData) {
        return Result.ofSuccess(iOrderFromService.attentionList(orderFromAttentionParamData));
    }

    @Override
    public Result<TailPage<OrderFromHistoryDownLoadVTO>> historyDownload(OrderFromHistoryDownLoadParamData orderFromHistoryDownLoadParamData) {
        return Result.ofSuccess(iOrderFromService.historyDownload(orderFromHistoryDownLoadParamData));
    }

    @Override
    public Result<Boolean> saveAttention(OrderFromAttentionSaveParamData orderFromAttentionSaveParamData) {
        return Result.ofSuccess(iAttentionService.saveAttention(orderFromAttentionSaveParamData));
    }

    @Override
    public Result<Boolean> delAttention(OrderFromAttentionDelParamData orderFromAttentionDelParamData) {
        return Result.ofSuccess(iAttentionService.delAttention(orderFromAttentionDelParamData));
    }

    @Override
    public Result<Boolean> checkAttentionDatainf(Long checkUserId, Long checkDataId) {
        return Result.ofSuccess(iAttentionService.checkAttentionDatainf(checkUserId,checkDataId));
    }

    @Override
    public Result<Boolean> orderfromDelete(String orderfromId) {
        return Result.ofSuccess(iOrderFromService.orderfromDelete(orderfromId));
    }

    @Override
    public Result<OrderFromInfoVTO> auditOrder(OrderAuditParamData paramData) {
        return Result.ofSuccess(iOrderFromService.auditOrder(paramData));
    }

    @Override
    public Result<List<OrderFromInfoVTO>> checkInOrOut(Long userId, List<Long> idList, Integer checkStatus) {
        return  Result.ofSuccess(iOrderFromService.checkInOrOut(userId,idList,checkStatus));
    }

    @Override
    public Result<Integer> isAttention(OrderFromAttentionSaveParamData orderFromAttentionSaveParamData) {
        return Result.ofSuccess(iOrderFromService.isAttention(orderFromAttentionSaveParamData));
    }

    @Override
    public Result<Integer> isOrder(Long dataInfoId, Long downloadUserId) {
        return Result.ofSuccess(iOrderFromService.isOrder(dataInfoId,downloadUserId));
    }

    @Override
    public Result<Integer> isOutOfDate(Long id) {
        return Result.ofSuccess(iOrderFromService.isOutOfDate(id));
    }
}
