package com.piesat.school.biz.ds.information.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piesat.school.biz.ds.information.builder.InformationBuilder;
import com.piesat.school.biz.ds.information.entity.Information;
import com.piesat.school.biz.ds.information.mapper.InformationMapper;
import com.piesat.school.biz.ds.information.service.IInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.biz.ds.user.entity.User;
import com.piesat.school.biz.ds.user.mapper.UserMapper;
import com.piesat.school.emuerlation.BizEnumType;
import com.piesat.school.information.param.InformationAddParamData;
import com.piesat.school.information.param.InformationPageParam;
import com.piesat.school.information.vto.InformationVTO;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lapaus
 * @since 2022-04-11
 */
@Service
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements IInformationService {

    @Resource
    private InformationMapper informationMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public InformationVTO addOrUpdateInformation(InformationAddParamData paramData) {
        Information information=new Information();
        Boolean isUpdate=false;
        if(paramData.getId()!=null){
            information=this.informationMapper.selectById(paramData.getId());
            isUpdate=true;
        }
        if(StringUtils.isNotBlank(paramData.getSynopsis())){
            information.setSynopsis(paramData.getSynopsis());
        }
        if(StringUtils.isNotBlank(paramData.getContent())){
            information.setContent(paramData.getContent());
        }
        if(paramData.getPublisher()!=null){
            information.setPublisher(paramData.getPublisher());
        }
        if(StringUtils.isNotBlank(paramData.getTitle())){
            information.setTitle(paramData.getTitle());
        }
        if(paramData.getType()!=null){
            information.setType(paramData.getType());
        }
        if(!isUpdate){
            this.informationMapper.insert(information);
        }else {
            this.updateById(information);
        }
        InformationVTO vto=new InformationVTO();
        BeanUtils.copyProperties(information,vto);
        return vto;
    }

    @Override
    public Boolean delInformation(Long id) {
        if(id!=null){
          Information  information=this.informationMapper.selectById(id);
          information.setStatus(BizEnumType.CommonStatus.Invalid.getKey());
          this.informationMapper.updateById(information);
        }
        return Boolean.TRUE;
    }

    @Override
    public InformationVTO detail(Long id) {
        InformationVTO vto=new InformationVTO();
        if(id!=null){
            Information  information=this.informationMapper.selectById(id);
            BeanUtils.copyProperties(information,vto);
            if(information.getPublisher()!=null){
                User user=userMapper.selectById(information.getPublisher());
                vto.setPublisherName(user.getName());
            }
        }
        return vto;
    }

    @Override
    public TailPage<InformationVTO> informationPage(InformationPageParam paramData) {
        QueryWrapper<Information> queryWrapper = new QueryWrapper<>();
        List<User> users=new ArrayList<>();
        if (paramData.getType() != null) {
            queryWrapper.eq("type", paramData.getType());
        }
        queryWrapper.eq("status", BizEnumType.CommonStatus.Valid.getKey());
        Page<Information> page = super.page(new Page<>(paramData.getPn(), paramData.getPs()), queryWrapper.orderByDesc("updated_at"));
        if (page.getRecords().size()!=0){
            List<Long> publisherIds = page.getRecords().stream().map(Information::getPublisher).collect(Collectors.toList());
            users = userMapper.selectBatchIds(publisherIds);
        }
        return CommonPage.buildPage(page.getCurrent(), page.getSize(), page.getTotal(), InformationBuilder.toVTO(page.getRecords(),users));
    }
}
