package com.saas.sisyphus.chat.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zard
 * @date Created in 12/01/2022 00:10
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyBaseMessage {

    private String FromUserName;
    //  开发者微信号
    private String ToUserName;
    //  消息创建时间 （整型）
    private String CreateTime;
    private String MsgType;
    //  消息id，64位整型
    private String MediaId;
}
