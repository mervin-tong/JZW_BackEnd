package com.piesat.school.biz.ds.datainf.service.impl;

import com.piesat.school.biz.ds.datainf.builder.DatainfBuilder;
import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.piesat.school.biz.ds.datainf.mapper.DatainfMapper;
import com.piesat.school.biz.ds.datainf.service.IDatainfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piesat.school.datainf.vto.DataInfVTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 数据信息表 服务实现类
 * </p>
 *
 * @author 周悦尧
 * @since 2022-01-27
 */
@Service
public class DatainfServiceImpl extends ServiceImpl<DatainfMapper, Datainf> implements IDatainfService {

    @Autowired
    private DatainfMapper datainfMapper;
    @Override
    public List<DataInfVTO> getAllDatainf() {
        List<Datainf> datas = datainfMapper.getAllDatainf();
        return DatainfBuilder.toDataInfVtos(datas);
    }
}
