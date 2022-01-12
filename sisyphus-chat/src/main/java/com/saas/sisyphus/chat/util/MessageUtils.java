package com.saas.sisyphus.chat.util;

import com.saas.sisyphus.chat.enums.MessageTypeEnum;
import com.saas.sisyphus.chat.model.domain.ReplyTextMessage;
import com.thoughtworks.xstream.XStream;

/**
 * @author zard
 * @date Created in 12/01/2022 00:10
 */
public class MessageUtils {

    /**
     * ReplyTextMessage 转化成 XML
     * @param replyTextMessage
     * @return
     */
    private String replyTextMessageToXml(ReplyTextMessage replyTextMessage) {
        XStream xStream = new XStream();
        xStream.alias("xml", replyTextMessage.getClass());
        return xStream.toXML(replyTextMessage);
    }

    /**
     * 封装XML格式的"发送文本消息"
     * @param fromUserName 粉丝appId
     * @param toUserName 公众号appId
     * @param content XML格式的字符串
     * @return
     */
    public String initReplyTextMessage(String fromUserName, String toUserName, String content) {
        ReplyTextMessage text = new ReplyTextMessage();
        text.setToUserName(fromUserName);
        text.setFromUserName(toUserName);
        text.setMsgType(MessageTypeEnum.RECEIVE_MESSAGE_TEXT.getType());
        long time = System.currentTimeMillis();
        text.setCreateTime(String.valueOf(time));
        text.setContent(content);
        return replyTextMessageToXml(text);
    }
}
