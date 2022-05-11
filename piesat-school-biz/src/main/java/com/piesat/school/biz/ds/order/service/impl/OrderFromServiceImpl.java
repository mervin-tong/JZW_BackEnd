package com.piesat.school.biz.ds.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.piesat.school.biz.ds.datainf.mapper.DatainfMapper;
import com.piesat.school.biz.ds.datareview.entity.DataReview;
import com.piesat.school.biz.ds.order.bulider.OrderBuilder;
import com.piesat.school.biz.ds.order.entity.Attention;
import com.piesat.school.biz.ds.order.entity.OrderFrom;
import com.piesat.school.biz.ds.order.mapper.AttentionMapper;
import com.piesat.school.biz.ds.order.mapper.OrderFromMapper;
import com.piesat.school.biz.ds.order.service.IOrderFromService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.datareview.vto.DataReviewReVTO;
import com.piesat.school.i18n.ResponseErrorCode;
import com.piesat.school.order.param.*;
import com.piesat.school.order.vto.OrderFromAttentionVTO;
import com.piesat.school.order.vto.OrderFromHistoryDownLoadVTO;
import com.piesat.school.order.vto.OrderFromInfoVTO;
import com.piesat.school.order.vto.OrderFromVTO;
import com.smartwork.api.exception.SmartworkI18nException;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 唐子超
 * @since 2022-01-17
 */
@Service
public class OrderFromServiceImpl extends ServiceImpl<OrderFromMapper, OrderFrom> implements IOrderFromService {

    @Resource
    private OrderFromMapper orderFromMapper;
    @Resource
    private AttentionMapper attentionMapper;
    @Resource
    private DatainfMapper datainfMapper;
    //获取订单列表
    @Override
    public TailPage<OrderFromVTO> orderFromMenu(OrderFromMenuPageParamData orderFromMenuPageParamData) {
        Page<OrderFromVTO> page = new Page<>(orderFromMenuPageParamData.getPn(),orderFromMenuPageParamData.getPs());
        page.setOptimizeCountSql(false);
        List<OrderFromVTO> list = orderFromMapper.orderFromMenu(orderFromMenuPageParamData, page);
        list.forEach(e->{
            if(e.getUpdatedAt()!=null&&(new Date()).getTime()>(e.getUpdatedAt().getTime()+3*24*60*60*1000)&&e.getDataType()==2){
                e.setDataType(4l);
            }
        });
        List<OrderFromVTO> result;
        if(orderFromMenuPageParamData.getDataType().equals(2L)) {
            result= list.stream().filter(e -> Long.valueOf("2").equals(e.getDataType())).collect(Collectors.toList());
        }else if (orderFromMenuPageParamData.getDataType().equals(4L)) {
            result=list.stream().filter(e -> Long.valueOf("4").equals(e.getDataType())).collect(Collectors.toList());
        }else {
            result=list;
        }
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),result);
    }
    //创建订单
    @Override
    public Boolean orderFromCreate(OrderFromParamData orderFromParamData) {
        if (orderFromParamData == null ||
                orderFromParamData.getDownloadUserId() == null ||
                orderFromParamData.getDataInfoId() == null){
            throw new SmartworkI18nException(ResponseErrorCode.MANAGEMENT_DATE_DENIED);
        }
        OrderFrom orderFrom = new OrderFrom();
        orderFrom.setCreatedAt(new Date());
        orderFrom.setDataInfoId(orderFromParamData.getDataInfoId());
        orderFrom.setAuditorUserId(orderFromParamData.getAuditorUserId());
        orderFrom.setDownloadUserId(orderFromParamData.getDownloadUserId());
        orderFrom.setMark(orderFromParamData.getDeclare());
        orderFrom.setDataType(1); //默认状态为审核中
        //如果数据是公开的则为已审核(通过)
        if(orderFromParamData.getStatus() == 1L){
            orderFrom.setDataType(2);
        }
        return this.save(orderFrom);
    }

    @Override
    public OrderFromInfoVTO orderFromInfo(Long orderFromId) {
        OrderFromInfoVTO orderFromInfoVTO=new OrderFromInfoVTO();
        OrderFrom orderFrom=orderFromMapper.selectById(orderFromId);
        Datainf datainf = datainfMapper.selectById(orderFrom.getDataInfoId());
        BeanUtils.copyProperties(orderFrom,orderFromInfoVTO);
        orderFromInfoVTO.setDataName(datainf.getDataName());
        if(orderFrom.getUpdatedAt()!=null&&((new Date()).getTime()>(orderFrom.getUpdatedAt().getTime()+3*24*60*60*1000))&&orderFrom.getDataType()==2){
            orderFromInfoVTO.setDataType(4);
        }
        return orderFromInfoVTO;
    }

    @Override
    public TailPage<OrderFromAttentionVTO> attentionList(OrderFromAttentionParamData orderFromAttentionParamData) {
        Page<OrderFromAttentionVTO> page = new Page<>(orderFromAttentionParamData.getPn(),orderFromAttentionParamData.getPs());
        page.setOptimizeCountSql(false);//关闭mybatis自动优化
        List<OrderFromAttentionVTO> list = orderFromMapper.attentionList(orderFromAttentionParamData, page);
        for(OrderFromAttentionVTO order : list){
            List<OrderFrom> orderFrom = orderFromMapper.selectList(new QueryWrapper<OrderFrom>().eq("data_info_id", order.getDataId()).eq("is_delete",0));
            if(orderFrom.size()!=0){
                order.setIsAddOrder(1);
            }else {
                order.setIsAddOrder(0);
            }
        }
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }

    @Override
    public TailPage<OrderFromHistoryDownLoadVTO> historyDownload(OrderFromHistoryDownLoadParamData orderFromHistoryDownLoadParamData) {
        Page<OrderFromHistoryDownLoadVTO> page = new Page<>(orderFromHistoryDownLoadParamData.getPn(),orderFromHistoryDownLoadParamData.getPs());
//        page.setOptimizeCountSql(false);//关闭mybatis自动优化
        List<OrderFromHistoryDownLoadVTO> list = orderFromMapper.historyDownload(orderFromHistoryDownLoadParamData, page);
        list.forEach(e->{
            if((e.getCreatedAt().getTime()+7*24*60*60*1000)>=(new Date()).getTime()){
                e.setIsDownloadAble(true);
            }else {
                e.setIsDownloadAble(false);
            }

        });
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }

    @Override
    public Boolean orderfromDelete(String orderfromId) {
        List<Long> longs = new ArrayList<>();
        String[] split = orderfromId.split(",");
        for (String s : split) {
            longs.add(Long.valueOf(s));
        }
        return orderFromMapper.orderfromDelete(longs);

    }

    @Override
    public OrderFromInfoVTO auditOrder(OrderAuditParamData paramData) {
        OrderFromInfoVTO vto=new OrderFromInfoVTO();
        if(paramData.getOrderId()!=null){
            OrderFrom orderFrom=this.orderFromMapper.selectById(paramData.getOrderId());
            if(orderFrom!=null){
                orderFrom.setAuditMark(paramData.getAuditMark());
                orderFrom.setUpdatedAt(new Date());
                orderFrom.setDataType(paramData.getAuditStatus());
                this.orderFromMapper.updateById(orderFrom);
                BeanUtils.copyProperties(orderFrom,vto);
            }
        }
        return vto;
    }

    @Override
    public List<OrderFromInfoVTO> checkInOrOut(Long userId, List<Long> idList, Integer checkStatus) {
        List<OrderFromInfoVTO> orderFromInfoVTOS =new ArrayList<>();
        List<OrderFrom> orders = this.listByIds(idList);
        if(checkStatus == 0){
            for(OrderFrom order:orders){
                OrderFromInfoVTO orderFromInfoVTO=new OrderFromInfoVTO();
                order.setAuditorUserId(-1L);
                this.updateById(order);
                BeanUtils.copyProperties(order, orderFromInfoVTO);
                orderFromInfoVTOS.add(orderFromInfoVTO);
            }
        }else {
            for(OrderFrom order:orders){
                OrderFromInfoVTO orderFromInfoVTO=new OrderFromInfoVTO();
                order.setAuditorUserId(userId);
                this.updateById(order);
                BeanUtils.copyProperties(order, orderFromInfoVTO);
                orderFromInfoVTOS.add(orderFromInfoVTO);
            }
        }
        return orderFromInfoVTOS;
    }

    @Override
    public Integer isAttention(OrderFromAttentionSaveParamData orderFromAttentionSaveParamData) {
        List<Attention> attention = attentionMapper.selectList(new QueryWrapper<Attention>().eq("user_id",orderFromAttentionSaveParamData.getUserId()).eq("data_id", orderFromAttentionSaveParamData.getDataId()));
        if (attention.size()!=0) {
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Integer isOrder(Long dataInfoId, Long downloadUserId) {
        List<OrderFrom> orderFrom=baseMapper.selectList(new QueryWrapper<OrderFrom>().eq("data_info_id",dataInfoId).eq("download_user_id",downloadUserId));
        if (orderFrom.size()!=0) {
            return 1;
        }else {
            return 0;
        }
    }
}
