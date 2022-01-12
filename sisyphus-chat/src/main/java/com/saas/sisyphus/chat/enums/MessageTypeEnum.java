package com.saas.sisyphus.chat.enums;

import lombok.Getter;

/**
 * @author zard
 * @date Created in 12/01/2022 00:08
 */
@Getter
public enum MessageTypeEnum {
    //    接收普通消息类型【0开头表示接收的消息类型】 start
    /** 被动回复文本消息 */
    RECEIVE_MESSAGE_TEXT(001, "text"),
    RECEIVE_MESSAGE_IMAGE(002, "image"),
    RECEIVE_MESSAGE_VOICE(003, "voice"),
    RECEIVE_MESSAGE_LOCATION(004, "location"),
    RECEIVE_MESSAGE_LINK(005, "link"),
    RECEIVE_MESSAGE_SHORTVIDEO(06, "shortvideo"),
    RECEIVE_MESSAGE_VIDEO(007, "video"),
//    接收普通消息类型【0开头表示接收的消息类型】 end
    ;
    /** 消息类型编号 */
    private Integer code;
    /** 消息类型（和微信文档中保持一致） */
    private String type;

    MessageTypeEnum(Integer code, String type) {
        this.code = code;
        this.type = type;
    }
}

