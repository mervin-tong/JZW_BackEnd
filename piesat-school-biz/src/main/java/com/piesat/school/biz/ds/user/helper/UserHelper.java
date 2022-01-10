package com.piesat.school.biz.ds.user.helper;

import com.piesat.school.biz.common.BizProperties;
import com.piesat.school.biz.ds.user.entity.User;
import org.apache.commons.lang3.StringUtils;

/**
 * @author lawliet
 * @date 2021-07-05
 * <p>
 * 描述内容
 */
public class UserHelper {
    /**
     * 当用户昵称为空时返回默认昵称
     * 当用户昵称存在时正常返回
     * @param user
     * @return
     */
    public static String userNicknameNonDefault(String userId, User user){
        if(StringUtils.isEmpty(userId) && (user == null || StringUtils.isEmpty(user.getId()))) return null;
        if(user == null || StringUtils.isEmpty(user.getNickname())){
            String _userId = StringUtils.isNotEmpty(userId) ? userId : user.getId();
            return "用户" + StringUtils.substring(_userId, 0, 4);
        }
        return user.getNickname();
    }

    public static String userNicknameNonDefault(String userId, String nickname){
        if(StringUtils.isNotEmpty(nickname)) return nickname;
        if(StringUtils.isNotEmpty(userId)) {
            return "用户" + StringUtils.substring(userId, 0, 4);
        }
        return null;
    }
    /**
     * 当用户头像为空时返回默认头像
     * 当用户头像存在时正常返回
     * @param user
     * @return
     */
//    public static String userAvatarNonDefault(User user){
//        if(user == null || user.getAvatar() == null){
//            return BizProperties.getUserDefaultAvatar();
//        }
//        return user.getAvatar();
//    }
//
//    public static String userAvatarNonDefault(String avatar){
//        if(StringUtils.isNotEmpty(avatar)) return avatar;
//        return BizProperties.getUserDefaultAvatar();
//    }

}
