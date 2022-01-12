package com.saas.sisyphus.chat.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zard
 * @date Created in 12/01/2022 00:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyTextMessage extends ReplyBaseMessage {

    /** 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示） */
    private String Content;
}
