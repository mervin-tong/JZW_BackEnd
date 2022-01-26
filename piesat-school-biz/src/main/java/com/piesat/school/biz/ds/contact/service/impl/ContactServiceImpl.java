package com.piesat.school.biz.ds.contact.service.impl;

import com.piesat.school.biz.ds.contact.entity.Contact;
import com.piesat.school.biz.ds.contact.mapper.ContactMapper;
import com.piesat.school.biz.ds.contact.service.IContactService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 联系人表 服务实现类
 * </p>
 *
 * @author 周悦尧
 * @since 2022-01-17
 */
@Service
public class ContactServiceImpl extends ServiceImpl<ContactMapper, Contact> implements IContactService {

}
