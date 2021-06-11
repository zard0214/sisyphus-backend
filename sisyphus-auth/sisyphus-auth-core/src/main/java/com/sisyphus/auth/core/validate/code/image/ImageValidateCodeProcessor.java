package com.sisyphus.auth.core.validate.code.image;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sisyphus.auth.core.SecurityResult;
import com.sisyphus.auth.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:42
 */
@Component
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void send(ServletWebRequest request, ImageCode imageCode) throws Exception {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(imageCode.getImage(), "JPEG", bos);

        SecurityResult result = SecurityResult.ok(bos.toByteArray());
        String json = objectMapper.writeValueAsString(result);
        HttpServletResponse response = request.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}