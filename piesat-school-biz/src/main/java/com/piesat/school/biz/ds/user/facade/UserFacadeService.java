package com.piesat.school.biz.ds.user.facade;

import com.piesat.school.biz.ds.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author lawliet
 * @date 2021-06-24
 * <p>
 * 描述内容
 */
@Slf4j
@Service
public class UserFacadeService {
    @Autowired
    private IUserService userService;

//    @Autowired
//    private IUserConfigService userConfigService;

    /**
     * 用户信息保存
     * 如果本地不存在用户数据，会创建用户数据
     * @param paramData
     */
    /*public Boolean userInfoSave(UserInfoSaveParamData paramData){
        User user = new User();
        user.setId(paramData.getUserId());
        user.setNickname(paramData.getNickname());
        user.setAvatar(paramData.getAvatar());
        user.setAreaCode(paramData.getAreaCode());
        user.setAreaName(paramData.getAreaName());
        userService.saveOrUpdate(user);

        UserConfig userConfig = new UserConfig();
        userConfig.setId(paramData.getUserId());
        List<String> _tagCategorys = paramData.getTagCategorys();
        if(_tagCategorys != null){
            userConfig.setTagCategorys(BizEnumType.TagCategory.whitespaceFromKeys(paramData.getTagCategorys()));
            userConfig.setTags(BizEnumType.TagCategory.whitespaceTagsFromKeys(paramData.getTagCategorys()));
        }
        userConfigService.saveOrUpdate(userConfig);
        return true;
    }
    *//**
     * PieEngine User Register/Login Callback
     * 触发school用户数据存储
     * @param pieAccountActionMessage
     *//*
    public void backupPieEngineUser(PieAccountActionMessage pieAccountActionMessage){
        Optional.ofNullable(pieAccountActionMessage).ifPresent(_account->{
            String userId = _account.getContent().getUserId();
            Optional.ofNullable(userId).ifPresent(_userId->{
                User user = userService.getById(userId);
                if(user == null){
                    user = new User();
                    user.setId(userId);
                    user.setAvatar(BizProperties.getUserDefaultAvatar());
                    user.setGender(0);
                    //user.setNickname();
                    userService.save(user);
                    log.info("backup pie engine user <{}> successful", userId);
                }
            });
        });
    }*/
}
