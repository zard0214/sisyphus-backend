package com.saas.sisyphus.chat.web;

import com.saas.sisyphus.chat.enums.MessageTypeEnum;
import com.saas.sisyphus.chat.service.ChatTranslateService;
import com.saas.sisyphus.chat.util.CheckUtil;
import com.saas.sisyphus.chat.util.MessageUtils;
import com.saas.sisyphus.chat.util.TransformUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author zard
 * @date Created in 09/01/2022 17:53
 */
@Slf4j
@RestController
@RequestMapping(value = "/chat", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Web - TranslateController", produces = MediaType.APPLICATION_JSON_VALUE)
public class TranslateController {

    @Resource
    private ChatTranslateService translateService;

    @GetMapping("/receiveMessage")
    public void login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
                out.write(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    @PostMapping("/receiveMessage")
    public void receiveMessage(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Map<String, String> receiveMap = TransformUtils.xml2Map(request);
        String fromUserName = receiveMap.get("FromUserName");
        String toUserName = receiveMap.get("ToUserName");
        String msgType = receiveMap.get("MsgType");
        String content = receiveMap.get("Content");
        String msgId = receiveMap.get("MsgId");
        String message = "";
        log.info("msgType :" +  msgType);
        log.info("MessageTypeEnum.RECEIVE_MESSAGE_TEXT.getType().equals(msgType) :" +  MessageTypeEnum.RECEIVE_MESSAGE_TEXT.getType().equals(msgType));
        if (MessageTypeEnum.RECEIVE_MESSAGE_TEXT.getType().equals(msgType)) {
            message = new MessageUtils().initReplyTextMessage(fromUserName, toUserName, translateService.translate(content));
        }
        out.print(message);
    }

}
