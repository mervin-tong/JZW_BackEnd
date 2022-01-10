package com.piesat.school.i18n;

import com.smartwork.api.errorcode.IErrorCode;

/**
 * user业务异常枚举
 * 6位业务错误码
 * 系统码2位 业务码2位 业务细节2位
 * 框架通用系统码10
 * school 系统码 11
 * @author Lawliet
 */
public enum ResponseErrorCode implements IErrorCode {

    USER_SESSION_LOGIN_DENIED("110001", "user.session.login.denied"),
    USER_SESSION_LOGIN_EXPIRE("110002", "user.session.login.expire"),
    USER_AUTHORIZE_DENIED("110003", "user.authorize.denied"),
    USER_DATASCOPE_DENIED("110004", "user.datascope.denied"),

    MANAGEMENT_DATE_DENIED("110101", "management.dateformat.denied"),

    NOTIFICATION_PUSH_STATUS_ILLEGAL("111001", "notification.push.status.illegal"),
    MESSAGE_SEND_STATUS_ILLEGAL("111101", "message.send.status.illegal"),
    MESSAGE_SEND_FAIL("111102", "message.send.fail"),
//    CONTENT_DATA_NOT_EXIST("110101", "content.data.notexist"),
//    CONTENT_DATA_STATUS_ILLEGAL("110102", "content.data.status.illegal"),
//    CONTENT_TOP_ILLEGAL("110103", "content.top.illegal"),
//    CONTENT_PUBLICACCOUNT_DATA_NOT_EXIST("110105", "content.publicaccount.data.notexist"),
//    CONTENT_PUBLICACCOUNT_DATA_STATUS_ILLEGAL("110106", "content.publicaccount.data.status.illegal"),
//    CONTENT_OPERATION_NO_PERMISSION("110110", "content.operation.nopermission"),
//    CONTENT_PUBLISHED_AFTER_OPERATION("110111", "content.published.after.operation"),
//
//    SUBJECT_DATA_NOT_EXIST("110151", "subject.data.notexist"),
//    SUBJECT_DATA_STATUS_ILLEGAL("110152", "subject.data.status.illegal"),
//    //SUBJECT_PUBLISHED_AFTER_OPERATION("110153", "subject.published.after.operation"),
//    SUBJECT_PUBLISH_MUSTHAS_BLOCK("110155", "subject.publish.musthas.block"),
//    SERIES_SUBJECT_DATA_NOT_EXIST("110171", "series.subject.data.notexist"),
//    SERIES_SUBJECT_DATA_STATUS_ILLEGAL("110172", "series.subject.data.status.illegal"),
//    SERIES_PUBLISH_MUSTHAS_SUBJECT("110175", "series.publish.musthas.subject"),
//    SUBJECT_BLOCK_DATA_NOT_EXIST("110181", "subject.block.data.notexist"),
//    SUBJECT_BLOCK_DATA_STATUS_ILLEGAL("110182", "subject.block.data.status.illegal"),
//    SUBJECT_COMMENT_DATA_NOT_EXIST("110191", "subject.comment.data.notexist"),
//    SUBJECT_COMMENT_OPERATION_NO_PERMISSION("110193", "subject.comment.operation.nopermission"),
//
//
//    TOPIC_DATA_NOT_EXIST("110141", "topic.data.notexist"),
//    TOPIC_DATA_STATUS_ILLEGAL("110142", "topic.data.status.illegal"),

    USER_COMMON_OPERATION_PERMIT("110400", "user.common.operation.permit"),
    ;

    private final String code;
    private final String i18n;

    ResponseErrorCode(String code, String i18n) {
        this.code = code;
        this.i18n = i18n;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String i18n() {
        return i18n;
    }
}
