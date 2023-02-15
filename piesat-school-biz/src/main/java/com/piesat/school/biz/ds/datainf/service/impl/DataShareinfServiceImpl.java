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
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

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
    public Result<String> keyToUrl(DataShareParamData dataShareParamData) {
//      传入参数key，返回url
        QueryWrapper<DataShareinf> queryWrapper=new QueryWrapper<>();
        String URL="/apps/school/hsd/";
        String apiKey=dataShareParamData.getApiKey();
        queryWrapper.eq("api_key",apiKey);
        DataShareinf dataShareinf=baseMapper.selectOne(queryWrapper);
        if (dataShareinf!=null) {

            dataShareinf.setUrl(URL + apiKey+"/"+apiKey+".rar");
            baseMapper.updateById(dataShareinf);
            return Result.ofSuccess(dataShareinf.getUrl());
        }
        return Result.ofFail("200001","key输入不正确");
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
//      传入参数申请表中的id,是否传入mark（mark为空表示通过），邮箱

        JavaMailSenderImpl javaMailSender=new JavaMailSenderImpl();

        DataShareinf dataShareinf=dataShareinfMapper.selectById(dataShareParamData.getId());
//       判断是否已经申请过，申请过则不会进行发送邮件、创建文件操作
        if (StringUtils.isNotBlank(dataShareinf.getApiKey())){
            return Result.ofFail("2000001","已申请过");
        }
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
        javaMailSender.setPort(587);



        if (dataShareParamData.getMark()!=null){
//            不通过
            dataShareinf.setApplyStatus(0);
            dataShareinf.setMark(dataShareParamData.getMark());
        }
        else {
//            通过，信息发送到邮箱
            dataShareinf.setApplyStatus(1);
            dataShareinf.setMark("");
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

//              创建文件夹并复制临时缓存文件夹
                File packages = new File("\\apps\\school\\hsd\\" + key);
                if (!packages.exists()) {
                    System.out.println(packages.mkdir());
                }

//              复制文件夹下的第一个压缩包文件到该文件夹
                //TODO 需补足处理：对方文件名称不定（该位置应查找上传文件夹下的第一个压缩包文件），而不是自己指定名称
                File currentDir=new File("\\apps\\school\\hsd\\target");
                File[] files=currentDir.listFiles();
                String targetFile="\\apps\\school\\hsd\\1.rar";  //设置一个默认名称
                if (files!=null&&files.length>0){
                    targetFile=files[0].getPath();
                }
                File source = new File(targetFile);
                //TODO 需补足处理：以当前逻辑处理，会导致每次该方法被执行会新建一个新的下载文件，占用大量内存；在实际使用中，若文件过大，则用户在一定时间内使用兑换的url去下载只会得到破损的文件
                File dest = new File(packages.getPath() + "\\"  + key + ".rar");
                try {
                    System.out.println(Files.copy(source.toPath(), dest.toPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } catch (MailException | MessagingException e) {
                e.printStackTrace();
            }
            dataShareinf.setApiKey(key);
            dataShareinf.setPassDate(new Date());

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


}
