package com.piesat.school.biz.ds.helpcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.base.PageQueryParamData;
import com.piesat.school.biz.ds.helpcenter.builder.HelpCenterBuilder;
import com.piesat.school.biz.ds.helpcenter.entity.HelpCenter;
import com.piesat.school.biz.ds.helpcenter.mapper.HelpCenterMapper;
import com.piesat.school.biz.ds.helpcenter.service.IHelpCenterService;
import com.piesat.school.biz.ds.user.entity.User;
import com.piesat.school.biz.ds.user.service.IUserService;
import com.piesat.school.helpcenter.vto.HelpCenterVTO;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HelpCenterServiceImpl extends ServiceImpl<HelpCenterMapper, HelpCenter> implements IHelpCenterService {
    @Resource
    private IUserService userService;
    @Override
    public Boolean deleteByids(String ids) {
        List<Long> list = Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
        return this.update(new UpdateWrapper<HelpCenter>().in("id", list).set("is_delete",1));
    }

    @Override
    public Boolean updateInfos(HelpCenterVTO helpCenterVTO) {
        HelpCenter helpCenter = new HelpCenter();
        BeanUtils.copyProperties(helpCenterVTO,helpCenter);
        helpCenter.setId(Long.parseLong(helpCenterVTO.getId()));
        User user = userService.getById(Long.parseLong(helpCenterVTO.getUploadUser()));
        helpCenter.setUploadUser(user.getName());
        return this.updateById(helpCenter);
    }

    @Override
    public Boolean saveInfos(HelpCenterVTO helpCenterVTO) {
        HelpCenter helpCenter = new HelpCenter();
        BeanUtils.copyProperties(helpCenterVTO,helpCenter);
        helpCenter.setIsDelete(0);
        User user = userService.getById(Long.parseLong(helpCenterVTO.getUploadUser()));
        helpCenter.setUploadUser(user.getName());
        return this.save(helpCenter);
    }

    @Override
    public List<HelpCenterVTO> getList() {
        List<HelpCenter> list = this.list(new LambdaQueryWrapper<HelpCenter>().eq(HelpCenter::getIsDelete,0));
        return HelpCenterBuilder.toHelpCenterVTOs(list);
    }

    @Override
    public TailPage<HelpCenterVTO> getPage(PageQueryParamData query) {
        Page<HelpCenter> page = this.page(new Page<>(query.getPn(),query.getPs()),new LambdaQueryWrapper<HelpCenter>().orderByAsc(HelpCenter::getIsDelete));
        List<HelpCenterVTO> list = HelpCenterBuilder.toHelpCenterVTOs(page.getRecords());
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);
    }

    @Override
    public HelpCenterVTO detail(String id) {
        HelpCenter helpCenter = this.getById(Long.parseLong(id));
        return HelpCenterBuilder.toVTO(helpCenter);
    }
}
