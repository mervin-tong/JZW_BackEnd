package com.piesat.school.biz.ds.datainf.service.impl;

import com.piesat.school.biz.ds.dataclass.bulider.DataClassBuilder;
import com.piesat.school.biz.ds.dataclass.entity.Dataclass;
import com.piesat.school.biz.ds.datainf.entity.Datainf;
import com.piesat.school.biz.ds.datainf.mapper.DatainfMapper;
import com.piesat.school.biz.ds.datainf.service.IDatainfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 数据信息表 服务实现类
 * </p>
 *
 * @author 周悦尧
 * @since 2022-01-17
 */
@Service
public class DatainfServiceImpl extends ServiceImpl<DatainfMapper, Datainf> implements IDatainfService {

    @Autowired
    private DatainfMapper datainfMapper;
    @Override
    public List<Datainf> getAllDatainf() {
        List<Datainf> datas = datainfMapper.getAllDatainf();
        return DataClassBuilder.toDataClassVtos(datas);
    }
}
