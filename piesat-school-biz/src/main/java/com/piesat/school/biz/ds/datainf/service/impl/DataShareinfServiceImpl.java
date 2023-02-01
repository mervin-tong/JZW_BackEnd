package com.piesat.school.biz.ds.datainf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.biz.ds.datainf.entity.DataShareinf;
import com.piesat.school.biz.ds.datainf.entity.SystemEmail;
import com.piesat.school.biz.ds.datainf.mapper.DataShareinfMapper;
import com.piesat.school.biz.ds.datainf.mapper.SystemEmailMapper;
import com.piesat.school.biz.ds.datainf.service.IDataShareinfService;
import com.piesat.school.datainf.param.AuditApplyListParamData;
import com.piesat.school.datainf.param.DataShareParamData;
import com.piesat.school.datainf.param.SystemEmailParamData;
import com.piesat.school.datainf.vto.AuditApplyListVTO;
import com.piesat.school.datainf.vto.ShareInfVTO;
import com.piesat.school.datainf.vto.SystemEmailVTO;
import com.smartwork.api.Result;
import com.smartwork.api.support.page.CommonPage;
import com.smartwork.api.support.page.TailPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * @Author: liqiteng
 * @Date: 2022/8/23
 * @Description:
 */

@Service
public class DataShareinfServiceImpl extends ServiceImpl<DataShareinfMapper, DataShareinf> implements IDataShareinfService{



    @Resource
    private  DataShareinfMapper dataShareinfMapper;

    @Resource
    private SystemEmailMapper systemEmailMapper;




    @Override
    public TailPage<ShareInfVTO> dataList(DataShareParamData paramData) {
//      审核申请列表查询
        Page<ShareInfVTO> page = new Page<>(paramData.getPn(),paramData.getPs());
        page.setOptimizeCountSql(false);
        List<ShareInfVTO> list = dataShareinfMapper.searchAll(paramData,page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);

    }

    @Override
    public Result<ShareInfVTO> applyForKey(DataShareParamData paramData) {
//      传入applyExplain、applyId
        ShareInfVTO shareInfVTO=new ShareInfVTO();
        if (paramData.getApplyId()!=null){
           QueryWrapper<DataShareinf> queryWrapper=new QueryWrapper<>();
           queryWrapper.lambda().eq(DataShareinf::getApplyId,paramData.getApplyId()).orderByDesc(DataShareinf::getUpdatedAt);
            List<DataShareinf> dataShareInfos=this.list(queryWrapper);
            if(dataShareInfos!=null&&dataShareInfos.size()>0){
                shareInfVTO=new ShareInfVTO();
                BeanUtils.copyProperties(dataShareInfos.get(0),shareInfVTO);
                shareInfVTO.setApplyExplain(paramData.getApplyExplain());
                DataShareinf dataShareinf=new DataShareinf();
                Date date=new Date();
                shareInfVTO.setUpdatedAt(date);
                shareInfVTO.setCreatedAt(date);
                if (shareInfVTO.getApplyStatus()==2){
                    BeanUtils.copyProperties(shareInfVTO,dataShareinf);
                    dataShareinfMapper.updateById(dataShareinf);
                }
                else if (shareInfVTO.getApplyStatus()==0){
                    shareInfVTO.setApplyStatus(2);
                    BeanUtils.copyProperties(shareInfVTO,dataShareinf);
                    dataShareinfMapper.insert(dataShareinf);
                }

            }
            else {
                BeanUtils.copyProperties(paramData,shareInfVTO);
                shareInfVTO.setApplyStatus(2);
                DataShareinf dataShareinf=new DataShareinf();
                BeanUtils.copyProperties(shareInfVTO,dataShareinf);
                dataShareinfMapper.insert(dataShareinf);
            }
        }
        else {
            return null;
        }


        return Result.ofSuccess(shareInfVTO);
    }


    @Override
    public Result<ShareInfVTO> checkStatus(DataShareParamData paramData) {
//      判断状态，0表示审核不通过，1表示审核通过，2表示审核中，传入参数applyId
        ShareInfVTO vto=null;
        QueryWrapper<DataShareinf> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(DataShareinf::getApplyId,paramData.getApplyId()).orderByDesc(DataShareinf::getUpdatedAt);
        List<DataShareinf> dataShareInfos=this.list(queryWrapper);
        if(dataShareInfos!=null&&dataShareInfos.size()>0){
            vto=new ShareInfVTO();
            BeanUtils.copyProperties(dataShareInfos.get(0),vto);
        }
        return Result.ofSuccess(vto);

    }


    @Override
    public Boolean keyToUrl(DataShareParamData dataShareParamData) {
//      传入参数key，返回true or false
        QueryWrapper<DataShareinf> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(DataShareinf::getApiKey,dataShareParamData.getApiKey());
        List<DataShareinf> dataShareInfos=this.list(queryWrapper);
        return dataShareInfos != null && dataShareInfos.size() > 0;
    }

    @Override
    public TailPage<AuditApplyListVTO> auditApplyList(AuditApplyListParamData auditApplyListParamData) {
        Page<AuditApplyListVTO> page = new Page<>(auditApplyListParamData.getPn(),auditApplyListParamData.getPs());
        page.setOptimizeCountSql(false);
        List<AuditApplyListVTO> list = dataShareinfMapper.auditApplyList(auditApplyListParamData,page);
        return CommonPage.buildPage(page.getCurrent(),page.getSize(),page.getTotal(),list);

    }

    @Override
    public ShareInfVTO detail(DataShareParamData dataShareParamData) {
        ShareInfVTO shareInfVTO=dataShareinfMapper.detail(dataShareParamData);


        return shareInfVTO;
    }

    @Override
    public Result<ShareInfVTO> pass(DataShareParamData dataShareParamData) {
//      传入参数申请表中的id,是否传入mark,邮箱
//      没有判断key是否有效

        JavaMailSenderImpl javaMailSender=new JavaMailSenderImpl();

        DataShareinf dataShareinf=dataShareinfMapper.selectById(dataShareParamData.getId());
//      生成随机key25位随机码
        int length = 25;
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        String key = sb.toString();
//===================================================================================================

        SystemEmail sender=systemEmailMapper.selectById(1);
        javaMailSender.setUsername(sender.getEmail());
        javaMailSender.setPassword(sender.getHotCode());
        javaMailSender.setHost("smtp."+sender.getEmail().substring(sender.getEmail().indexOf("@")+1));
        javaMailSender.setProtocol("smtp");
        javaMailSender.setPort(465);



        if (dataShareParamData.getMark()!=null){
//            不通过
            dataShareinf.setApplyStatus(0);
            dataShareinf.setMark(dataShareParamData.getMark());
        }
        else {
//            通过，信息发送到邮箱
            dataShareinf.setApplyStatus(1);
            dataShareinf.setMark(null);
            try {
                //建立邮件消息
                MimeMessage mimeMessage =  javaMailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

                //发送者
                mimeMessageHelper.setFrom(javaMailSender.getUsername());

                //接收者
                mimeMessageHelper.setTo(dataShareParamData.getEmail());

                //发送的标题
                mimeMessageHelper.setSubject("发送申请的key");

                //发送的内容
                mimeMessageHelper.setText("您申请的key是："+key);

                //发送邮件
                javaMailSender.send(mimeMessage);


            } catch (MailException | MessagingException e) {
                e.printStackTrace();
            }
            dataShareinf.setApiKey(key);
        }
        dataShareinfMapper.updateById(dataShareinf);
        ShareInfVTO vto=new ShareInfVTO();
        BeanUtils.copyProperties(dataShareinf,vto);
        return Result.ofSuccess(vto);
    }


    @Override
    public Result<SystemEmailVTO> setEmail(SystemEmailParamData systemEmailParamData) {
        SystemEmail email=new SystemEmail();
        SystemEmailVTO emailVTO=new SystemEmailVTO();
        systemEmailParamData.setId((long) 1);
        BeanUtils.copyProperties(systemEmailParamData,email);
        systemEmailMapper.updateById(email);
        BeanUtils.copyProperties(email,emailVTO);
        return Result.ofSuccess(emailVTO);
    }

    @Override
    public SystemEmailVTO seeEmail() {
        SystemEmailVTO systemEmailVTO=new SystemEmailVTO();
        BeanUtils.copyProperties(systemEmailMapper.selectById(1),systemEmailVTO);
        return systemEmailVTO;
    }

    @Override
    public String random(String url) {
        List<String> stringList = Arrays.asList(url.split(""));
        Collections.shuffle(stringList);
        StringBuffer Url=new StringBuffer();
        stringList.forEach(Url::append);

        return Url.toString();
    }



    }
