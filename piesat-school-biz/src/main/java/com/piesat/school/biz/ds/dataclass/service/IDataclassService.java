package com.piesat.school.biz.ds.dataclass.service;

import com.piesat.school.biz.ds.dataclass.entity.Dataclass;
import com.baomidou.mybatisplus.extension.service.IService;
import com.piesat.school.dataclass.vto.DataClassVTO;

import java.util.List;

/**
 * <p>
 * 数据分类表 服务类
 * </p>
 *
 * @author 周悦尧
 * @since 2022-01-17
 */
public interface IDataclassService extends IService<Dataclass> {
    List<DataClassVTO> getAllDataClass();

}
