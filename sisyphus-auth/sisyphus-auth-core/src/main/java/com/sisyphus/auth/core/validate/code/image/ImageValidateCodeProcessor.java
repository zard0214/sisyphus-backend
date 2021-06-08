package com.sisyphus.auth.core.validate.code.image;

import com.sisyphus.auth.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:42
 */
@Component
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Override
    public void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        HttpServletResponse response = request.getResponse();
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }
}